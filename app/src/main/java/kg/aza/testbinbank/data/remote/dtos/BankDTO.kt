package kg.aza.testbinbank.data.remote.dtos

import kg.aza.testbinbank.domain.model.BankModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BankDTO(
    @SerialName("name")
    val name: String? = null
)

fun BankDTO.toDomain() : BankModel = BankModel(
    name = this.name.orEmpty()
)


