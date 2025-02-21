package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.designsystem.transformed.cardNumberTransformedText


@Composable
fun CardNumberTextFiled(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = { cardNumberTransformedText(it) },
        label = { Text(stringResource(R.string.text_filed_label_card_number)) },
        placeholder = { Text(stringResource(R.string.text_filed_placeholder_card_number)) },
        modifier = modifier
            .fillMaxWidth()
            .semantics { contentDescription = "CardNumberTextFiled" },
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberTextFiledPreview() {
    var input = remember { mutableStateOf<String>("1234123412341234") }

    PaymentsTheme {
        CardNumberTextFiled(
            value = input.value,
            onValueChange = {
                input.value = it.take(16)
            }
        )
    }
}