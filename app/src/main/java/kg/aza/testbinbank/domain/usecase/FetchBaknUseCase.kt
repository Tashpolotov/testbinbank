package kg.aza.testbinbank.domain.usecase

import kg.aza.testbinbank.domain.repository.BankRepository


class FetchBankUseCase(private val bankRepository: BankRepository) {
    operator fun invoke(bin:String) = bankRepository.fetchBank(bin)
}