package kg.aza.testbinbank.core

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.http.URLBuilder
import io.ktor.http.headers
import io.ktor.http.path
import io.ktor.util.StringValues
import kg.balance.reforged.foundation.infrastructure.local.preferences.UserPreferences
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class HttpService(val httpClient: HttpClient) : KoinComponent {

    protected val userPreferences by inject<UserPreferences>()

    protected fun URLBuilder.appendQuery(name: String, value: String?) {
        value?.let { nonNullValue ->
            parameters.append(name, nonNullValue)
        }
    }

    protected fun URLBuilder.appendQuery(vararg queries: String?) {
        parameters.appendAll(StringValues.build {
            queries
                .asSequence()
                .chunked(2)
                .mapNotNull { pair ->
                    if (pair.size == 2) pair[0]?.let { key ->
                        pair[1]?.let { value ->
                            key to value
                        }
                    } else null
                }
                .toMap().forEach(::append)
        })
    }

    protected fun URLBuilder.appendHeader(name: String, value: String?) {
        value?.let { nonNullValue ->
            headers {
                append(name, nonNullValue)
            }
        }
    }


    protected suspend inline fun <reified T> get(
    path: String,
    request: HttpRequestBuilder.() -> Unit = {},
    crossinline url: URLBuilder.() -> Unit = {}
): T {
    val response = httpClient.get {
        request()
        url {
            path(path)
            url()
        }
    }
    return response.body()
}

    protected suspend inline fun <reified T> post(
        path: String,
        request: HttpRequestBuilder.() -> Unit = {},
        crossinline url: URLBuilder.() -> Unit = {}
    ) = httpClient.post {
        request()
        url {
            path(path)
            url()
        }
    }

    protected suspend inline fun <reified T> patch(
        path: String,
        request: HttpRequestBuilder.() -> Unit = {},
        crossinline url: URLBuilder.() -> Unit = {}
    ) = httpClient.patch {
        request()
        url {
            path(path)
            url()
        }
    }

    protected suspend inline fun <reified T> put(
        path: String,
        request: HttpRequestBuilder.() -> Unit = {},
        crossinline url: URLBuilder.() -> Unit = {}
    ) = httpClient.put {
        request()
        url {
            path(path)
            url()
        }
    }

    protected suspend inline fun <reified T> delete(
        path: String,
        request: HttpRequestBuilder.() -> Unit = {},
        crossinline url: URLBuilder.() -> Unit = {}
    ) = httpClient.delete {
        request()
        url {
            path(path)
            url()
        }
    }
}