package kg.aza.testbinbank.presentation.view.models

import kg.aza.testbinbank.domain.model.CountryModel


data class CountryUIModel(
    val numeric: String,
    val alpha2: String,
    val name: String,
    val emoji: String,
    val currency: String,
    val latitude: Int,
    val longitude: Int
)

fun CountryModel.toUI(): CountryUIModel {
    return CountryUIModel(
        numeric = numeric,
        alpha2 = alpha2,
        name = name,
        emoji = emoji,
        currency = currency,
        latitude = latitude,
        longitude = longitude
    )
}