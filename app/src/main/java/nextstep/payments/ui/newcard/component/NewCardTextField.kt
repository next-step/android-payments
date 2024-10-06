package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NewCardTextField(
    label: String,
    placeHolder: String,
    text: String,
    setText: (String) -> Unit,
    maxLength: Int,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        value = text,
        onValueChange = { newText ->
            if (newText.length <= maxLength || newText.length < text.length) setText(newText)
        },
        label = { Text(label) },
        placeholder = { Text(placeHolder) },
        modifier = modifier.fillMaxWidth(),
        visualTransformation = visualTransformation,
    )
}

@Preview(showBackground = true)
@Composable
private fun NewCardTextFieldPreview() {
    NewCardTextField(
        label = "label",
        placeHolder = "placeHolder",
        text = "text",
        setText = { },
        maxLength = 10,
    )
}
