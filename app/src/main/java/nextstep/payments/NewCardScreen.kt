package nextstep.payments

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.component.CardInputField
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    NewCardScreen(
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        onCardNumberChange = viewModel::setCardNumber,
        onExpiredDateChange = viewModel::setExpiredDate,
        onOwnerNameChange = viewModel::setOwnerName,
        onPasswordChange = viewModel::setPassword,
        modifier = modifier,
    )
}

@Composable
fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { NewCardTopBar(onBackClick = { }, onSaveClick = { }) },
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

            CardInputField(
                value = cardNumber,
                onValueChange = onCardNumberChange,
                labelText = stringResource(R.string.card_number_label),
                placeHolderText = stringResource(R.string.card_number_placeholder),
            )

            CardInputField(
                value = expiredDate,
                onValueChange = onExpiredDateChange,
                labelText = stringResource(R.string.card_expired_date_label),
                placeHolderText = stringResource(R.string.card_expired_date_placeholder),
            )

            CardInputField(
                value = ownerName,
                onValueChange = onOwnerNameChange,
                labelText = stringResource(R.string.card_owner_name_label),
                placeHolderText = stringResource(R.string.card_owner_name_placeholder),
            )

            CardInputField(
                value = password,
                onValueChange = onPasswordChange,
                labelText = stringResource(R.string.card_password_label),
                placeHolderText = stringResource(R.string.card_password_placeholder),
            )
        }
    }
}

@Preview
@Composable
private fun StatefulNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            viewModel = NewCardViewModel().apply {
                setCardNumber(cardNumber = "0000 - 0000 - 0000 - 0000")
                setExpiredDate("00 / 00")
                setOwnerName("홍길동")
                setPassword("0000")
            }
        )
    }
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            cardNumber = "0000 - 0000 - 0000 - 0000",
            expiredDate = "00 / 00",
            ownerName = "홍길동",
            password = "0000",
            onCardNumberChange = {},
            onExpiredDateChange = {},
            onOwnerNameChange = {},
            onPasswordChange = {},
        )
    }
}