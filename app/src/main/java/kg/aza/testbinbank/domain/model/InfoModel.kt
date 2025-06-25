package kg.aza.testbinbank.domain.model

data class InfoModel(
    val scheme: String,
    val type: String,
    val brand: String,
    val country: CountryModel?,
    val bank: BankModel?
)
