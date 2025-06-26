import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kg.aza.testbinbank.R
import kg.aza.testbinbank.app_components.BaseButton
import kg.aza.testbinbank.app_components.VerticalSpacer
import kg.aza.testbinbank.core.OpenMap
import kg.aza.testbinbank.core.UIState
import kg.aza.testbinbank.presentation.nav_graph.Route
import kg.aza.testbinbank.presentation.view.mainScreen.BankViewModel
import kg.aza.testbinbank.presentation.view.models.InfoUIModel
import kg.balance.reforged.foundation.infrastructure.local.preferences.UserPreferences
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.koinInject

@Composable
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val bankViewModel: BankViewModel = koinViewModel()
    val context = LocalContext.current
    var binInput by remember { mutableStateOf("") }
    val bankState by bankViewModel.bank.collectAsState()
    val userPreferences: UserPreferences = koinInject()
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(bottom = 72.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        BaseTextField(
            value = binInput,
            onValueChange = { binInput = it },
            label = stringResource(R.string.enter_bin),
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Spacer(Modifier.height(16.dp))
        BaseButton(
            modifier = Modifier.fillMaxWidth(),
            textButton = stringResource(R.string.search),
            onClickButton = {
                if (binInput.isNotBlank() && binInput.length >= 6) {
                    bankViewModel.fetchBank(binInput)
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.min_6_lenght), Toast.LENGTH_SHORT
                    ).show()
                }
            }
        )

        Spacer(Modifier.height(16.dp))

        when (val state = bankState) {
            is UIState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UIState.Success -> {
                BankInfoDisplay(state.data, navController)
                userPreferences.addToHistory(binInput, state.data)
            }

            is UIState.Error -> Text(
                text = stringResource(
                    R.string.error,
                    state.error.errorMessage
                )
            )

            else -> Unit
        }
    }
    Box(modifier = Modifier.fillMaxSize()) {
        BaseButton(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp),
            textButton = stringResource(R.string.look_save),
            onClickButton = {
                navController.navigate(Route.HistoryScreen.route)
            }
        )
    }
}

@Composable
fun BankInfoDisplay(info: InfoUIModel, navController: NavController) {
    val context = LocalContext.current
    val latitude = info.country?.latitude
    val longitude = info.country?.longitude

    Text(
        text = stringResource(
            R.string.name_bank,
            info.bank?.name ?: stringResource(R.string.unknown)
        )
    )
    VerticalSpacer()
    Text(
        text = stringResource(
            R.string.country,
            info.country?.name ?: stringResource(R.string.unknown)
        )
    )
    Spacer(Modifier.height(8.dp))

    if (latitude != null && longitude != null) {
        Text(
            text = stringResource(R.string.latitude, latitude.toString(), longitude.toString()),
            modifier = Modifier.clickable {
                OpenMap(context, latitude, longitude)
            },
            color = Color.Blue,
            textDecoration = TextDecoration.Underline
        )
    } else {
        Text(text = stringResource(R.string.latitude, stringResource(R.string.unknown)))
    }

    VerticalSpacer()
    Text(text = stringResource(R.string.brand, info.brand ?: stringResource(R.string.unknown)))
    VerticalSpacer()
    Text(
        text = stringResource(
            R.string.currency,
            info.country?.currency ?: stringResource(R.string.unknown)
        )
    )
    VerticalSpacer()
    Text(
        text = stringResource(
            R.string.emoji,
            info.country?.emoji ?: stringResource(R.string.unknown)
        )
    )
}

