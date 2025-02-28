package nextstep.payments.screens.card.new

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.components.card.EmptyPaymentCard
import nextstep.payments.screens.card.new.components.NewCardTopBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewCardScreen(
    navigateToCardList: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()

    LaunchedEffect(cardAdded) {
        if (cardAdded) navigateToCardList()
    }

    NewCardScreen(
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        onCardNumberChange = viewModel::setCardNumber,
        onExpiredDateChange = viewModel::setExpiredDate,
        onOwnerNameChange = viewModel::setOwnerName,
        onPasswordChange = viewModel::setPassword,
        onBackClick = onBackClick,
        onSaveClick = viewModel::addCard,
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
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { NewCardTopBar(onBackClick = onBackClick, onSaveClick = onSaveClick) },
        modifier = modifier,
        containerColor = Color.White
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            EmptyPaymentCard()

            Spacer(modifier = Modifier.height(40.dp))

            CardInformationInputFields(
                cardNumber = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
                password = password,
                onCardNumberChange = onCardNumberChange,
                onExpiredDateChange = onExpiredDateChange,
                onOwnerNameChange = onOwnerNameChange,
                onPasswordChange = onPasswordChange,
            )
        }
    }
}

@Composable
private fun CardInformationInputFields(
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
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = expiredDate,
            onValueChange = onExpiredDateChange,
            label = { Text(stringResource(R.string.new_card_expiration_day_label)) },
            placeholder = { Text(stringResource(R.string.new_card_expiration_day_placeholder)) },
            modifier = Modifier.fillMaxWidth(fraction = 0.5f),
        )

        OutlinedTextField(
            value = ownerName,
            onValueChange = onOwnerNameChange,
            label = { Text(stringResource(R.string.new_card_card_owner_name_label)) },
            placeholder = { Text(stringResource(R.string.new_card_card_owner_name_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(stringResource(R.string.new_card_password_label)) },
            placeholder = { Text(stringResource(R.string.new_card_password_placeholder)) },
            modifier = Modifier.fillMaxWidth(fraction = 0.5f),
            visualTransformation = PasswordVisualTransformation(),
        )
    }
}

@Preview
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen(
        cardNumber = "0000 - 0000 - 0000 - 0000",
        expiredDate = "00 / 00",
        ownerName = "홍길동",
        password = "0000",
        onCardNumberChange = {},
        onExpiredDateChange = {},
        onOwnerNameChange = {},
        onPasswordChange = {},
        onBackClick = {},
        onSaveClick = {},
    )
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
