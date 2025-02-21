package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.designsystem.theme.PaymentsTheme


@Composable
fun CardPasswordTextFiled(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.text_filed_label_password)) },
        placeholder = { Text(stringResource(R.string.text_filed_placeholder_password)) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = "CardPasswordTextFiled" },
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview
@Composable
private fun CardPasswordTextFiledPreview() {

    var input by remember { mutableStateOf("1234123412341234") }

    PaymentsTheme {
        CardPasswordTextFiled(
            value = input,
            onValueChange = { input = it }
        )
    }

}