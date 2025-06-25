package kg.aza.testbinbank.core


sealed class UIState<out T> {
    class Idle<T> : UIState<T>()

    class Loading<T> : UIState<T>()

    class Error<T>(val error: HttpResponseError) : UIState<T>()

    class Success<T>(val data: T) : UIState<T>()
}