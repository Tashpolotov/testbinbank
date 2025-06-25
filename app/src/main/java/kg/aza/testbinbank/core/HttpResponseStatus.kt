import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class HttpResponseStatus {
    SUCCESS,
    FAIL,
    ERROR,
    EXCEPTION,
    INFO;

    fun isErrorOrFail(): Boolean = this == ERROR || this == FAIL
}

object HttpResponseStatusSerializer : KSerializer<HttpResponseStatus> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("HttpResponseStatus", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: HttpResponseStatus) {
        encoder.encodeString(value.name)
    }

    override fun deserialize(decoder: Decoder): HttpResponseStatus {
        val rawValue = decoder.decodeString().trim().uppercase()

        val statusFromCode = rawValue.toIntOrNull()?.let { code ->
            when (code) {
                500 -> HttpResponseStatus.ERROR
                in 200..299 -> HttpResponseStatus.SUCCESS
                in 400..499 -> HttpResponseStatus.FAIL
                else -> HttpResponseStatus.INFO
            }
        }

        if (statusFromCode != null) return statusFromCode

        return when (rawValue) {
            "SUCCESS" -> HttpResponseStatus.SUCCESS
            "FAIL" -> HttpResponseStatus.FAIL
            "ERROR" -> HttpResponseStatus.ERROR
            "EXCEPTION" -> HttpResponseStatus.EXCEPTION
            "INFO" -> HttpResponseStatus.INFO
            else -> throw IllegalArgumentException("Unknown status: $rawValue")
        }
    }
}
