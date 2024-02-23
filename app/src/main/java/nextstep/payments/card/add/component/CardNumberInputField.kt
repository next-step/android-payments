package nextstep.payments.card.add.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R

@Composable
fun CardNumberInputField(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    InputField(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(id = R.string.input_field_card_number_label),
        hint = stringResource(id = R.string.input_field_card_number_hint),
        value = value,
        onValueChanged = onValueChanged,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberInputFieldPreview() {
    CardNumberInputField(
        value = "",
        onValueChanged = {},
    )
}
