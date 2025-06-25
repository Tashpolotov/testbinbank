package kg.aza.testbinbank.domain.repository

import kg.aza.testbinbank.core.typealiases.Either
import kg.aza.testbinbank.domain.model.InfoModel
import kotlinx.coroutines.flow.Flow

interface BankRepository {

    fun fetchBank(bin: String): Flow<Either<InfoModel>>

}