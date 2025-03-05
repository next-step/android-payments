package nextstep.payments.screens.card.update.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.screens.card.update.components.util.CardNumberVisualTransformation
import nextstep.payments.screens.card.update.components.util.ExpiredDateVisualTransformation
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardInformationInputFields(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        OutlinedTextField(
            value = cardNumber,
            onValueChange = onCardNumberChange,
            label = { Text(stringResource(R.string.new_card_card_number_label)) },
            placeholder = { Text(stringResource(R.string.new_card_card_number_placeholder)) },
            visualTransformation = CardNumberVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = expiredDate,
            onValueChange = onExpiredDateChange,
            label = { Text(stringResource(R.string.new_card_expiration_day_label)) },
            placeholder = { Text(stringResource(R.string.new_card_expiration_day_placeholder)) },
            visualTransformation = ExpiredDateVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth(fraction = 0.5f),
        )

        OutlinedTextField(
            value = ownerName,
            onValueChange = onOwnerNameChange,
            label = { Text(stringResource(R.string.new_card_card_owner_name_label)) },
            placeholder = { Text(stringResource(R.string.new_card_card_owner_name_placeholder)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(stringResource(R.string.new_card_password_label)) },
            placeholder = { Text(stringResource(R.string.new_card_password_placeholder)) },
            modifier = Modifier.fillMaxWidth(fraction = 0.5f),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            visualTransformation = PasswordVisualTransformation(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardInformationInputFieldsPreview() {
    PaymentsTheme {
        CardInformationInputFields(
            cardNumber = "",
            onCardNumberChange = {},
            expiredDate = "",
            onExpiredDateChange = {},
            ownerName = "",
            onOwnerNameChange = {},
            password = "",
            onPasswordChange = {}
        )
    }
}
