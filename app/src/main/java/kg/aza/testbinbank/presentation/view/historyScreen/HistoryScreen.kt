package kg.aza.testbinbank.presentation.view.historyScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kg.balance.reforged.foundation.infrastructure.local.preferences.UserPreferences
import org.koin.compose.koinInject

@Composable
fun HistoryScreen(navController: NavController) {
    val userPreferences: UserPreferences = koinInject()

    val historyList = userPreferences.historyList

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "История запросов", modifier = Modifier.padding(bottom = 8.dp))

        if (historyList.isEmpty()) {
            Text(text = "История пуста")
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(historyList) { entry ->
                    HistoryEntryItem(entry = entry, onClick = {})
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun HistoryEntryItem(entry: kg.aza.testbinbank.presentation.view.models.HistoryEntryUI, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Text(text = "BIN: ${entry.bin}")
        Text(text = "Bank: ${entry.bankName}")
        Text(text = "Country: ${entry.countryName} ${entry.countryEmoji}")
        Text(text = "Scheme: ${entry.scheme}")
        Text(text = "Type: ${entry.type}")
        Text(text = "Brand: ${entry.brand}")
    }
}
