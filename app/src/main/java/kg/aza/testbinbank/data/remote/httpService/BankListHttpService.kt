package kg.aza.testbinbank.data.remote.httpService


import io.ktor.client.HttpClient
import kg.aza.testbinbank.core.HttpService
import kg.aza.testbinbank.data.remote.dtos.InfoDTO

class BankHttpService(httpService: HttpClient) : HttpService(httpService) {

    suspend fun fetchBank(bin: String) = get<InfoDTO>(bin)
}
