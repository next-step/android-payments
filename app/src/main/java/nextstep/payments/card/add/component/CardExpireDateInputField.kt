package nextstep.payments.card.add.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R

@Composable
fun CardExpireDateInputField(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    InputField(
        label = stringResource(id = R.string.input_field_card_expire_date_label),
        hint = stringResource(id = R.string.input_field_card_expire_date_hint),
        value = value,
        onValueChanged = onValueChanged,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardExpireDateInputFieldPreview() {
    CardExpireDateInputField(
        value = "",
        onValueChanged = {},
    )
}
