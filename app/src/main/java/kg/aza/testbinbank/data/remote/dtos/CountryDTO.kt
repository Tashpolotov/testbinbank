package kg.aza.testbinbank.data.remote.dtos

import kg.aza.testbinbank.core.DTOMapper
import kg.aza.testbinbank.core.orEmpty
import kg.aza.testbinbank.domain.model.CountryModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryDTO(
    @SerialName("numeric")
    val numeric: String? = null,
    @SerialName("alpha2")
    val alpha2: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("emoji")
    val emoji: String? = null,
    @SerialName("currency")
    val currency: String? = null,
    @SerialName("latitude")
    val latitude: Int? = null,
    @SerialName("longitude")
    val longitude: Int? = null
)

fun CountryDTO.toDomain(): CountryModel {
    return CountryModel(
        numeric = this.numeric.orEmpty(),
        alpha2 = this.alpha2.orEmpty(),
        name = this.name.orEmpty(),
        emoji = this.emoji.orEmpty(),
        currency = this.currency.orEmpty(),
        latitude = this.latitude.orEmpty(),
        longitude = this.longitude.orEmpty()
        )
}