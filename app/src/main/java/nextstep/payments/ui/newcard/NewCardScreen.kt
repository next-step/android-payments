package nextstep.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.newcard.component.NewCardTopBar
import nextstep.payments.ui.util.CardNumberVisualTransformation
import nextstep.payments.ui.util.ExpirationDateVisualTransformation

@Composable
internal fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onAddCardClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onAddCardClick
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentCard()

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = cardNumber,
                onValueChange = { newValue ->
                    // 숫자가 아닌 문자를 필터링하여 새로운 값으로 설정
                    val filteredValue = newValue.filter { it.isDigit() }
                    // 16자 제한
                    if (filteredValue.length <= 16) {
                        setCardNumber(filteredValue)

                        if (filteredValue.length == 16) {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    }
                },
                label = { Text(text = stringResource(id = R.string.card_number)) },
                placeholder = { Text(text = stringResource(id = R.string.card_number_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = CardNumberVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(
                value = expiredDate,
                onValueChange = { newValue ->
                    // 숫자가 아닌 문자를 필터링하여 새로운 값으로 설정
                    val filteredValue = newValue.filter { it.isDigit() }
                    // 4자 제한
                    if (filteredValue.length <= 4) {
                        setExpiredDate(filteredValue)

                        if (filteredValue.length == 4) {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    }
                },
                label = { Text(text = stringResource(id = R.string.expiry_date)) },
                placeholder = { Text(text = stringResource(id = R.string.expiry_date_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = ExpirationDateVisualTransformation(),
            )

            OutlinedTextField(
                value = ownerName,
                onValueChange = setOwnerName,
                label = { Text(text = stringResource(id = R.string.card_owner_name)) },
                placeholder = { Text(text = stringResource(id = R.string.card_owner_name_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                )
            )

            OutlinedTextField(
                value = password,
                onValueChange = { newValue ->
                    // 숫자가 아닌 문자를 필터링하여 새로운 값으로 설정
                    val filteredValue = newValue.filter { it.isDigit() }
                    // 4자 제한
                    if (filteredValue.length <= 4) {
                        setPassword(filteredValue)
                    }
                },
                label = { Text(text = stringResource(R.string.password)) },
                placeholder = { Text(text = stringResource(R.string.password_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewNewCardScreen() {
    val cardNumber = remember { mutableStateOf("1111222233334444") }
    val expiredDate = remember { mutableStateOf("1234") }
    val ownerName = remember { mutableStateOf("홍길동") }
    val password = remember { mutableStateOf("1234") }

    NewCardScreen(
        cardNumber = cardNumber.value,
        expiredDate = expiredDate.value,
        ownerName = ownerName.value,
        password = password.value,
        setCardNumber = { cardNumber.value = it },
        setExpiredDate = { expiredDate.value = it },
        setOwnerName = { ownerName.value = it },
        setPassword = { password.value = it },
        onAddCardClick = {},
        onBackClick = {},
        modifier = Modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewEmptyNewCardScreen() {
    val cardNumber = remember { mutableStateOf("") }
    val expiredDate = remember { mutableStateOf("") }
    val ownerName = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    NewCardScreen(
        cardNumber = cardNumber.value,
        expiredDate = expiredDate.value,
        ownerName = ownerName.value,
        password = password.value,
        setCardNumber = { cardNumber.value = it },
        setExpiredDate = { expiredDate.value = it },
        setOwnerName = { ownerName.value = it },
        setPassword = { password.value = it },
        onAddCardClick = {},
        onBackClick = {},
        modifier = Modifier
    )
}