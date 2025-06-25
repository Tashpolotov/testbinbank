package kg.aza.testbinbank.app_components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BaseButton(
    textButton:String,
    onClickButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClickButton,
        modifier = modifier
    ) {
        Text(text = textButton)
    }
}



