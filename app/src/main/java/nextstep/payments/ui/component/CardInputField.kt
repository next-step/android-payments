package nextstep.payments.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
internal fun CardInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        visualTransformation = visualTransformation,
        modifier = modifier,
    )
}


@Preview(showBackground = true)
@Composable
private fun CardInputFieldPreview() {
    PaymentsTheme {
        val (value, onValueChange) = remember { mutableStateOf("") }
        CardInputField(
            value = value,
            onValueChange = onValueChange,
            label = "카드 번호",
            placeholder = "0000 - 0000 - 0000 - 0000",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
