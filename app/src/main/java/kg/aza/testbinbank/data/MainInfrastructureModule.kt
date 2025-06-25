package kg.aza.testbinbank.data

import kg.aza.testbinbank.core.KoinModule
import kg.aza.testbinbank.data.remote.httpService.BankHttpService
import kg.aza.testbinbank.domain.repository.BankRepository
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module
import kg.aza.testbinbank.data.repositoies.BankRepositoryImplementation


object MainInfrastructureModule : KoinModule() {

    override fun create(): Module {
        return module {
            single { BankHttpService(get(named("lookupHttpClient"))) }
            singleOf(::BankRepositoryImplementation) bind BankRepository::class
        }
    }
}