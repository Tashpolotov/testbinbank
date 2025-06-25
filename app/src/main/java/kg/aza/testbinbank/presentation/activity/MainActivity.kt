package kg.aza.testbinbank.presentation.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import kg.aza.testbinbank.core.FoundationPresentationModule
import kg.aza.testbinbank.presentation.MainModule
import kg.aza.testbinbank.presentation.nav_graph.NavGraph
import kg.aza.testbinbank.ui.theme.TestBinBankTheme
import org.koin.core.context.loadKoinModules

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadKoinModules(
            listOf(
                MainModule.create(),
                FoundationPresentationModule.create(),
            )
        )

        enableEdgeToEdge()
        setContent {
            TestBinBankTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}