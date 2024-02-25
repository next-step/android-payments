package nextstep.payments.card.add.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R

@Composable
fun CardPasswordInputField(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    InputField(
        label = stringResource(id = R.string.input_field_card_password_label),
        hint = stringResource(id = R.string.input_field_card_password_hint),
        value = value,
        onValueChanged = onValueChanged,
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview(showBackground = true)
@Composable
private fun CardPasswordInputFieldPreview() {
    CardPasswordInputField(
        value = "",
        onValueChanged = {},
    )
}
