package nextstep.payments.card.add.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
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
        isInputPassword = true,
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
