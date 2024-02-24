package nextstep.payments.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PaymentInputField(
    text: String,
    onTextChange: (String) -> Unit,
    label: String,
    hint: String,
    modifier: Modifier = Modifier,
    maxLength: Int = Int.MAX_VALUE,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    OutlinedTextField(
        value = text.take(maxLength),
        onValueChange = { onTextChange(it) },
        modifier = modifier.padding(top = 20.dp),
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(text = hint, color = Color.LightGray)
        },
        singleLine = true,
        visualTransformation = visualTransformation
    )
}

@Preview(showBackground = true)
@Composable
fun PaymentInputFieldPreview() {
    PaymentInputField(
        text = "test",
        onTextChange = {},
        label = "label",
        hint = "hint"
    )
}
