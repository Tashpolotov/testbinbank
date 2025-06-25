package kg.aza.testbinbank.presentation.nav_graph

import MainScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kg.aza.testbinbank.presentation.view.historyScreen.HistoryScreen
import androidx.compose.ui.Modifier

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Route.MainScreen.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Route.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(Route.HistoryScreen.route) {
            HistoryScreen(navController = navController)
        }
    }
}