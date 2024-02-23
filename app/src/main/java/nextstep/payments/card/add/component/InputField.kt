package nextstep.payments.card.add.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.payments.R

@Composable
fun InputField(
    label: String,
    hint: String,
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    isInputPassword: Boolean = false,
    inputTextMaxLength: Int = Int.MAX_VALUE,
    showInputValueLength: Boolean = false,
) {
    OutlinedTextField(
        modifier = modifier,
        label = {
            Text(text = label)
        },
        placeholder = {
            Text(
                text = hint,
                color = Color(0xFFAAAAAA),
            )
        },
        value = value.take(inputTextMaxLength),
        supportingText = {
            if ((inputTextMaxLength != Int.MAX_VALUE) && showInputValueLength) {
                TextLengthCounter(
                    currentLength = value.length,
                    limitLength = inputTextMaxLength,
                )
            }
        },
        onValueChange = onValueChanged,
        visualTransformation = when (isInputPassword) {
            true -> PasswordVisualTransformation()
            false -> VisualTransformation.None
        },
    )
}

@Composable
private fun TextLengthCounter(
    currentLength: Int,
    limitLength: Int,
) {
    Row {
        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = stringResource(
                id = R.string.input_field_text_length_format,
                currentLength,
                limitLength
            ),
            fontSize = 12.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun InputFieldPreview_Empty() {
    InputField(
        label = "Input Field Death",
        hint = "this is hint",
        value = "",
        onValueChanged = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun InputFieldPreview_Filled() {
    InputField(
        label = "Input Field Death",
        hint = "this is hint",
        value = "Filled",
        onValueChanged = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun InputFieldPreview_showTextLength() {
    InputField(
        label = "Input Field Death",
        hint = "this is hint",
        value = "Filled",
        onValueChanged = {},
        showInputValueLength = true,
        inputTextMaxLength = 30
    )
}

@Preview(showBackground = true)
@Composable
private fun InputFieldPreview_Password() {
    InputField(
        label = "Input Field Death",
        hint = "this is hint",
        value = "Filled",
        onValueChanged = {},
        isInputPassword = true,
    )
}
