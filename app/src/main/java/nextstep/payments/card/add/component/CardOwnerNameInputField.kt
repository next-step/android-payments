package nextstep.payments.card.add.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R

@Composable
fun CardOwnerNameInputField(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    InputField(
        modifier = Modifier.fillMaxWidth(),
        label = stringResource(id = R.string.input_field_card_owner_name_label),
        hint = stringResource(id = R.string.input_field_card_owner_name_hint),
        value = value,
        onValueChanged = onValueChanged,
        inputTextMaxLength = 30,
        showInputValueLength = true
    )
}

@Preview(showBackground = true)
@Composable
private fun CardOwnerNameInputFieldPreview() {
    CardOwnerNameInputField(
        value = "",
        onValueChanged = {},
    )
}
