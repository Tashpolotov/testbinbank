package kg.aza.testbinbank.core.typealiases
import arrow.core.Either
import kg.aza.testbinbank.core.HttpResponseError


typealias Either<T> = Either<HttpResponseError, T>