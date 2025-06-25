package kg.aza.testbinbank.data.repositoies

import kg.aza.testbinbank.core.typealiases.Either
import kg.aza.testbinbank.data.remote.dtos.toDomain
import kg.aza.testbinbank.data.remote.httpService.BankHttpService
import kg.aza.testbinbank.domain.model.InfoModel
import kg.aza.testbinbank.domain.repository.BankRepository
import kg.balance.reforged.foundation.infrastructure.RepositoryImplementation
import kotlinx.coroutines.flow.Flow

class BankRepositoryImplementation(private val bankHttpService: BankHttpService) :
    RepositoryImplementation(),
    BankRepository {

    override fun fetchBank(bin: String): Flow<Either<InfoModel>>{
        return executeNetworkRequest({
            bankHttpService.fetchBank(bin)
        }) {
            it.toDomain()
        }
    }
}

