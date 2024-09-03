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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.R
import nextstep.payments.ui.components.CardInfoBottomSheet
import nextstep.payments.ui.components.PaymentToolBar
import nextstep.payments.ui.newcard.model.BankUI
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.transformation.CardNumberTransformation
import nextstep.payments.ui.transformation.ExpiredDateTransformation

// Stateful
@Composable
internal fun NewCardScreen(
    viewModel: NewCardViewModel,
    navigateToCardList: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val newCardUiState by viewModel.newCardUiState.collectAsStateWithLifecycle()
    val cardAdded by viewModel.cardAdded.collectAsStateWithLifecycle()

    LaunchedEffect(cardAdded) {
        if (cardAdded) navigateToCardList()
    }

    NewCardScreen(
        newCardUiState = newCardUiState,
        onBankSelect = viewModel::setBank,
        onBackClick = onBackClick,
        onSaveClick = viewModel::addCard,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword
    )
}

// Stateless
@Composable
fun NewCardScreen(
    newCardUiState: NewCardUiState,
    onBankSelect: (BankUI) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    if (!newCardUiState.isCardSelected) {
        CardInfoBottomSheet(
            isBottomSheetVisible = newCardUiState.isBottomSheetVisible,
            onBankSelect = onBankSelect,
            onDismissRequest = { }
        )
    }

    Scaffold(
        topBar = {
            PaymentToolBar(
                title = stringResource(id = R.string.card_add_top_bar_title),
                onBackClick = onBackClick,
                onActionsClick = onSaveClick
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

            PaymentCard(newCardUiState.bankUI)

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = newCardUiState.cardNumber,
                onValueChange = { setCardNumber(it) },
                label = { Text(stringResource(id = R.string.card_number_label)) },
                isError = newCardUiState.isCardNumberEmptyError || newCardUiState.isCardNumberFormatError,
                supportingText = {
                    if (newCardUiState.isCardNumberEmptyError) {
                        Text(text = stringResource(id = R.string.card_number_empty_error_message))
                    } else if (newCardUiState.isCardNumberFormatError) {
                        Text(text = stringResource(id = R.string.card_number_format_error_message))
                    }
                },
                placeholder = { Text(stringResource(id = R.string.card_number_place_holder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = CardNumberTransformation,
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = newCardUiState.expiredDate,
                onValueChange = { setExpiredDate(it) },
                label = { Text(stringResource(id = R.string.expired_date_label)) },
                isError = newCardUiState.isExpiredDateEmptyError || newCardUiState.isExpiredDateFormatError,
                supportingText = {
                    if (newCardUiState.isExpiredDateEmptyError) {
                        Text(text = stringResource(id = R.string.expired_date_empty_error_message))
                    } else if (newCardUiState.isExpiredDateFormatError) {
                        Text(text = stringResource(id = R.string.expired_date_format_error_message))
                    }
                },
                placeholder = { Text(stringResource(id = R.string.expired_date_place_holder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = ExpiredDateTransformation,
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = newCardUiState.ownerName,
                onValueChange = { setOwnerName(it) },
                label = { Text(stringResource(id = R.string.owner_name_date_label)) },
                placeholder = { Text(stringResource(id = R.string.owner_name_place_holder)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = newCardUiState.password,
                onValueChange = { setPassword(it) },
                label = { Text(stringResource(id = R.string.password_name_date_label)) },
                isError = newCardUiState.isPasswordEmptyError || newCardUiState.isPasswordFormatError,
                supportingText = {
                    if (newCardUiState.isPasswordEmptyError) {
                        Text(text = stringResource(id = R.string.password_empty_error_message))
                    } else if (newCardUiState.isPasswordFormatError) {
                        Text(text = stringResource(id = R.string.password_format_error_message))
                    }
                },
                placeholder = { Text(stringResource(id = R.string.password_place_holder)) },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Preview
@Composable
private fun StatefulNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            navigateToCardList = { },
            onBackClick = { },
            viewModel = NewCardViewModel().apply {
                setCardNumber("0000 - 0000 - 0000 - 0000")
                setExpiredDate("00 / 00")
                setOwnerName("최용호")
                setPassword("1234")
            }
        )
    }
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            newCardUiState = NewCardUiState(
                cardNumber = "0000000000000000",
                expiredDate = "0000",
                ownerName = "최용호",
                password = "1234",
                bankUI = BankUI.BC
            ),
            onBankSelect = {},
            onBackClick = {},
            onSaveClick = {},
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {}
        )
    }
}
