package kg.aza.testbinbank.core

import android.content.Context
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpConfig
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kg.aza.testbinbank.core.serializers.FileSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.io.File
import java.util.concurrent.TimeUnit

object FoundationInfrastructureModule : KoinModule() {
    private const val LOOKUP_URL = "https://lookup.binlist.net/"
    override fun create(): Module {
        return module {
            single(named("json")) { generateJson() }
            single(named("lookupHttpClient")) {
                val context: Context = get()
                generateHttpClient(context) {
                    defaultRequest {
                        url(LOOKUP_URL)
                    }
                }
            }
        }
    }

    private fun generateJson() = Json {
        isLenient = true
        ignoreUnknownKeys = true
        explicitNulls = false
        prettyPrint = true
        serializersModule = SerializersModule {
            polymorphicDefaultSerializer(File::class) { FileSerializer }
        }
    }

    private fun generateHttpClient(
        context: Context,
        defaultRequestBlock: (DefaultRequest.DefaultRequestBuilder.() -> Unit)? = null,
        block: (HttpClientConfig<OkHttpConfig>.() -> Unit)? = null
    ) = HttpClient(OkHttp) {
        engine {
            config {
                callTimeout(60, TimeUnit.SECONDS)
                connectTimeout(60, TimeUnit.SECONDS)
                readTimeout(60, TimeUnit.SECONDS)
                writeTimeout(60, TimeUnit.SECONDS)
                followRedirects(false)
                followSslRedirects(false)

                addInterceptor(
                    HttpLoggingInterceptor().setLevel(
                        HttpLoggingInterceptor.Level.BODY
                    )
                )
            }
        }

        install(ContentNegotiation) {
            json(generateJson())
        }

        defaultRequest {
            contentType(ContentType.Application.Json)
            defaultRequestBlock?.invoke(this)
        }

        block?.invoke(this)
    }
}
