package kg.aza.testbinbank.domain

import kg.aza.testbinbank.core.KoinModule
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import kg.aza.testbinbank.domain.usecase.FetchBankUseCase

object MainLogicModule : KoinModule() {
    override fun create(): Module {
        return module {
            factoryOf(::FetchBankUseCase)
        }
    }
}