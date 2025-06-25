package kg.aza.testbinbank.presentation.view.models

import kotlinx.serialization.Serializable

@Serializable
data class HistoryEntryUI(
    val bin: String,
    val scheme: String,
    val type: String,
    val brand: String,
    val countryNumeric: String,
    val countryAlpha2: String,
    val countryName: String,
    val countryEmoji: String,
    val countryCurrency: String,
    val countryLatitude: Int,
    val countryLongitude: Int,
    val bankName: String
)

fun InfoUIModel.toHistoryEntry(bin: String): HistoryEntryUI {
    return HistoryEntryUI(
        bin = bin,
        scheme = scheme,
        type = type,
        brand = brand,
        countryNumeric = country?.numeric.orEmpty(),
        countryAlpha2 = country?.alpha2.orEmpty(),
        countryName = country?.name.orEmpty(),
        countryEmoji = country?.emoji.orEmpty(),
        countryCurrency = country?.currency.orEmpty(),
        countryLatitude = country?.latitude ?: 0,
        countryLongitude = country?.longitude ?: 0,
        bankName = bank?.name.orEmpty()
    )
}