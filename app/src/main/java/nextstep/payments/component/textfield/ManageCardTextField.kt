package nextstep.payments.component.textfield

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
fun CardNumberTextFiled(
    cardNumber: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = cardNumber,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.new_card_number_label)) },
        placeholder = { Text(text = stringResource(id = R.string.new_card_number_place_holder)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        visualTransformation = CardNumberVisualTransformation(),
        singleLine = true,
        modifier = modifier,
    )
}

@Composable
fun ExpiredDateTextFiled(
    expiredDate: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = expiredDate,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.new_card_expired_date_label)) },
        placeholder = { Text(text = stringResource(id = R.string.new_card_expired_date_place_holder)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        visualTransformation = ExpiredDateVisualTransformation(),
        singleLine = true,
        modifier = modifier,
    )
}

@Composable
fun OwnerNameTextFiled(
    ownerName: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = ownerName,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.new_card_owner_name_label)) },
        placeholder = { Text(text = stringResource(id = R.string.new_card_owner_name_place_holder)) },
        singleLine = true,
        modifier = modifier,
    )
}

@Composable
fun PasswordTextFiled(
    password: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = password,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.new_card_password_label)) },
        placeholder = { Text(text = stringResource(id = R.string.new_card_password_place_holder)) },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        visualTransformation = PasswordVisualTransformation(),
        singleLine = true,
        modifier = modifier,
    )
}

@Preview(showBackground = true, name = "CardNumberTextFiledWithValue")
@Composable
private fun Preview1() {
    PaymentsTheme {
        CardNumberTextFiled(
            cardNumber = "1234123412341234",
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true, name = "CardNumberTextFiled")
@Composable
private fun Preview2() {
    PaymentsTheme {
        CardNumberTextFiled(
            cardNumber = "",
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true, name = "ExpiredDateTextFiledWithValue")
@Composable
private fun Preview3() {
    PaymentsTheme {
        ExpiredDateTextFiled(
            expiredDate = "1212",
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true, name = "ExpiredDateTextFiled")
@Composable
private fun Preview4() {
    PaymentsTheme {
        ExpiredDateTextFiled(
            expiredDate = "",
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true, name = "OwnerNameTextFiledWithValue")
@Composable
private fun Preview5() {
    PaymentsTheme {
        OwnerNameTextFiled(
            ownerName = "김컴포즈",
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true, name = "OwnerNameTextFiled")
@Composable
private fun Preview6() {
    PaymentsTheme {
        OwnerNameTextFiled(
            ownerName = "",
            onValueChange = {}
        )
    }
}

@Preview(showBackground = true, name = "PasswordTextFiledWithValue")
@Composable
private fun Preview7() {
    PaymentsTheme {
        PasswordTextFiled(
            password = "1234",
            onValueChange = {}
        )
    }
}


@Preview(showBackground = true, name = "PasswordTextFiled")
@Composable
private fun Preview8() {
    PaymentsTheme {
        PasswordTextFiled(
            password = "",
            onValueChange = {}
        )
    }
}