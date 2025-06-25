package kg.aza.testbinbank.data.remote.dtos
import kg.aza.testbinbank.domain.model.InfoModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InfoDTO(
    @SerialName("scheme")
    val scheme: String? = null,
    @SerialName("type")
    val type: String? = null,
    @SerialName("brand")
    val brand: String? = null,
    @SerialName("country")
    val country: CountryDTO? = null,
    @SerialName("bank")
    val bank: BankDTO? = null
)

fun InfoDTO.toDomain(): InfoModel {
    return InfoModel(
        scheme = this.scheme.orEmpty(),
        type = this.type.orEmpty(),
        brand = this.brand.orEmpty(),
        country = this.country?.toDomain(),
        bank = this.bank?.toDomain()
    )
}