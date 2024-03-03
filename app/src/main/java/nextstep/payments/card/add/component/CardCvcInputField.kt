package nextstep.payments.card.add.component

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R

@Composable
fun CardCvcInputField(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    InputField(
        label = stringResource(id = R.string.input_field_card_cvc_label),
        hint = stringResource(id = R.string.input_field_card_cvc_hint),
        value = value,
        onValueChanged = onValueChanged,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun CardCvcInputFieldPreview() {
    CardCvcInputField(
        value = "",
        onValueChanged = {},
    )
}
