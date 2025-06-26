package kg.aza.testbinbank.presentation.view.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import kg.aza.testbinbank.core.UIState
import kg.aza.testbinbank.domain.usecase.FetchBankUseCase
import kg.aza.testbinbank.presentation.view.models.InfoUIModel
import kg.aza.testbinbank.presentation.view.models.toUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BankViewModel(
    private val fetchBankUseCase: FetchBankUseCase
) : ViewModel() {

    private val _bank = MutableStateFlow<UIState<InfoUIModel>>(UIState.Idle())
    val bank: StateFlow<UIState<InfoUIModel>> = _bank.asStateFlow()

    fun fetchBank(bin: String) {
        viewModelScope.launch {
            _bank.emit(UIState.Loading())

            fetchBankUseCase(bin).collect { result ->
                when (result) {
                    is Either.Left -> _bank.emit(UIState.Error(result.value))
                    is Either.Right -> _bank.emit(UIState.Success(result.value.toUI()))
                }
            }
        }
    }
}
