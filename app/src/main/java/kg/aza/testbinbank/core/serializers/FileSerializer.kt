package kg.aza.testbinbank.core.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.io.File

object FileSerializer : KSerializer<File> {
    override val descriptor: SerialDescriptor =
        PrimitiveSerialDescriptor("File", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: File) {
        encoder.encodeString(value.absolutePath)
    }

    override fun deserialize(decoder: Decoder): File {
        val path = decoder.decodeString()
        return File(path)
    }
}