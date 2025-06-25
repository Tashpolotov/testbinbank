package kg.aza.testbinbank.core

sealed class HttpResponseError(val errorMessage: String) {
    class ClientRequest(val error: String) : HttpResponseError(error)
    class RedirectResponse(val error: String) : HttpResponseError(error)
    class ServerResponse(val error: String) : HttpResponseError(error)
    class Unexpected(val error: String) : HttpResponseError(error)
}