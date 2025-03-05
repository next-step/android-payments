package nextstep.payments.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.data.model.Card
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.utils.CardNumberVisualTransformation
import nextstep.payments.utils.ExpirationDateVisualTransformation

@Composable
internal fun CardInputFields(
    card: Card,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 24.dp)
    ) {
        CardNumberInputField(
            value = card.number,
            onValueChange = onCardNumberChange,
        )
        OwnerNameInputField(
            value = card.ownerName,
            onValueChange = onOwnerNameChange,
        )
        ExpireDateInputField(
            value = card.expiredDate,
            onValueChange = onExpiredDateChange,
        )
        PasswordInputField(
            value = card.password,
            onValueChange = onPasswordChange,
        )
    }
}

@Composable
internal fun CardInputField(
    value: String,
    onValueChange: (String) -> Unit,
    maxLength: Int,
    labelText: String,
    placeHolderText: String,
    keyboardType: KeyboardType,
    visualTransformation: VisualTransformation,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        value = value,
        onValueChange = { it -> onValueChange(it.take(maxLength)) },
        label = { Text(labelText) },
        placeholder = { Text(placeHolderText) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
    )
}

@Composable
private fun CardNumberInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    CardInputField(
        value = value,
        onValueChange = onValueChange,
        maxLength = 16,
        labelText = stringResource(R.string.card_number_label),
        placeHolderText = stringResource(R.string.card_number_placeholder),
        keyboardType = KeyboardType.Number,
        visualTransformation = CardNumberVisualTransformation(),
    )
}

@Composable
private fun OwnerNameInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    CardInputField(
        value = value,
        onValueChange = onValueChange,
        maxLength = 20,
        labelText = stringResource(R.string.card_owner_name_label),
        placeHolderText = stringResource(R.string.card_owner_name_placeholder),
        keyboardType = KeyboardType.Text,
        visualTransformation = VisualTransformation.None,
    )
}

@Composable
private fun ExpireDateInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    CardInputField(
        value = value,
        onValueChange = onValueChange,
        maxLength = 4,
        labelText = stringResource(R.string.card_expired_date_label),
        placeHolderText = stringResource(R.string.card_expired_date_placeholder),
        keyboardType = KeyboardType.Number,
        visualTransformation = ExpirationDateVisualTransformation()
    )
}

@Composable
private fun PasswordInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    CardInputField(
        value = value,
        onValueChange = onValueChange,
        maxLength = 4,
        labelText = stringResource(R.string.card_password_label),
        placeHolderText = stringResource(R.string.card_password_placeholder),
        keyboardType = KeyboardType.NumberPassword,
        visualTransformation = PasswordVisualTransformation(),
    )
}

@Preview(showBackground = true)
@Composable
private fun CardNumberInputFieldPreview() {
    var cardNumber by remember { mutableStateOf("") }

    PaymentsTheme {
        Box(modifier = Modifier.fillMaxWidth()) {
            CardNumberInputField(
                value = cardNumber,
                onValueChange = { cardNumber = it },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OwnerNameInputFieldPreview() {
    var name by remember { mutableStateOf("") }

    PaymentsTheme {
        Box(modifier = Modifier.fillMaxWidth()) {
            OwnerNameInputField(
                value = name,
                onValueChange = { name = it },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ExpireDateInputFieldPreview() {
    var expiredDate by remember { mutableStateOf("") }

    PaymentsTheme {
        Box(modifier = Modifier.fillMaxWidth()) {
            ExpireDateInputField(
                value = expiredDate,
                onValueChange = { expiredDate = it },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PasswordInputFiledPreview() {
    var password by remember { mutableStateOf("") }

    PaymentsTheme {
        Box(modifier = Modifier.fillMaxWidth()) {
            PasswordInputField(
                value = password,
                onValueChange = { password = it },
            )
        }
    }
}