package kg.balance.reforged.foundation.infrastructure

import android.util.Log
import arrow.core.Either
import io.ktor.client.statement.HttpResponse
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.http.isSuccess
import kg.aza.testbinbank.core.HttpResponseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.component.KoinComponent

abstract class RepositoryImplementation : KoinComponent {

    protected inline fun <reified DTO, reified ANYTHING> makeNetworkRequest(
        crossinline request: suspend () -> Pair<HttpResponse, DTO>,
        crossinline successful: suspend (DTO) -> ANYTHING,
    ) = flow {
        val (response, body) = request()
        when {
            response.status.isSuccess() -> {
                emit(Either.Right(successful(body)))
            }

            response.status.value in 300..308 ->
                emit(
                    Either.Left(
                        HttpResponseError.RedirectResponse(
                            "<-- ${response.status.value} Redirection error: ${response.status.description}"
                        )
                    )
                )

            response.status.value in 400..499 ->
                emit(
                    Either.Left(
                        HttpResponseError.ClientRequest(
                            "<-- ${response.status.value} Client-side error: ${response.status.description}"
                        )
                    )
                )

            response.status.value in 500..599 ->
                emit(
                    Either.Left(
                        HttpResponseError.ServerResponse(
                            "<-- ${response.status.value} Server-side error: ${response.status.description}"
                        )
                    )
                )

            else ->
                emit(
                    Either.Left(
                        HttpResponseError.Unexpected(
                            "<-- ${response.status.value} Unexpected error: ${response.status.description}"
                        )
                    )
                )
        }
    }.processException()

    protected fun <T> Flow<Either<HttpResponseError, T>>.processException() =
        flowOn(Dispatchers.IO).catch { exception ->
            Log.e("RepositoryImplementation", exception.stackTraceToString())
            emit(
                Either.Left(
                    when (exception) {
                        is ClientRequestException -> HttpResponseError.ClientRequest(exception.response.status.description)
                        is ServerResponseException -> HttpResponseError.ServerResponse(exception.response.status.description)
                        is RedirectResponseException -> HttpResponseError.RedirectResponse(exception.response.status.description)
                        else -> HttpResponseError.Unexpected(
                            exception.localizedMessage ?: "Unknown error occurred"
                        )
                    }
                )
            )
        }

    protected inline fun <reified DTO, reified ANYTHING> executeNetworkRequest(
        crossinline request: suspend () -> DTO,
        crossinline modification: suspend (DTO) -> ANYTHING,
    ) = flow {
        try {
            val body = request()
            emit(Either.Right(modification(body)))
        } catch (e: Exception) {
            emit(Either.Left(HttpResponseError.Unexpected(e.localizedMessage ?: "Unknown error")))
        }
    }.flowOn(Dispatchers.IO)
}
