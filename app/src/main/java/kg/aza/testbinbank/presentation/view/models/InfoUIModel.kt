package kg.aza.testbinbank.presentation.view.models

import kg.aza.testbinbank.domain.model.InfoModel
import kotlinx.serialization.Serializable

data class InfoUIModel(
    val scheme: String,
    val type: String,
    val brand: String,
    val country: CountryUIModel?,
    val bank: BankUIModel?
)

fun InfoModel.toUI(): InfoUIModel {
    return InfoUIModel(
        scheme = scheme,
        type = type,
        brand = brand,
        country = country?.toUI(),
        bank = bank?.toUI()
    )
}
