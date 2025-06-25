package kg.aza.testbinbank.presentation.view.models

import kg.aza.testbinbank.domain.model.BankModel


data class BankUIModel(
    val name: String
)

fun BankModel.toUI() : BankUIModel {
    return BankUIModel(
        name = name
    )
}