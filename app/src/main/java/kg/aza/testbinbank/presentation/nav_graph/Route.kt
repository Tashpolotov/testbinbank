package kg.aza.testbinbank.presentation.nav_graph

sealed class Route(val route: String)  {
    object MainScreen : Route("main_screen")
    object HistoryScreen : Route("history_screen")
}