package kg.aza.testbinbank.core

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HttpResponse<T>(
    @SerialName("status")
    val status: HttpResponseStatus,
    @SerialName("data")
    val data: T? = null,
    @SerialName("message")
    val message: String? = null
)