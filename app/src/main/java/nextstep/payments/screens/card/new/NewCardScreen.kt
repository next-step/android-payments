package nextstep.payments.screens.card.new

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.components.card.EmptyPaymentCard
import nextstep.payments.components.card.NewPaymentCard
import nextstep.payments.screens.card.CardCompanyState
import nextstep.payments.screens.card.new.components.CardCompanyBottomSheetDialog
import nextstep.payments.screens.card.new.components.NewCardTopBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewCardScreen(
    navigateToCardList: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.cardAdded) {
        if (uiState.cardAdded) navigateToCardList()
    }

    NewCardScreen(
        uiState = uiState,
        onCardCompanyClick = viewModel::setSelectedCardCompany,
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
    uiState: NewCardUiState,
    onCardCompanyClick: (CardCompanyState) -> Unit,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var showCardCompanyBottomSheet by remember { mutableStateOf(true) }

    Scaffold(
        topBar = { NewCardTopBar(onBackClick = onBackClick, onSaveClick = onSaveClick) },
        modifier = modifier,
        containerColor = Color.White
    ) { innerPadding ->
        if (showCardCompanyBottomSheet) {
            CardCompanyBottomSheetDialog(
                onDismissRequest = { showCardCompanyBottomSheet = false },
                onCardCompanyClick = onCardCompanyClick,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            if (uiState.selectedCardCompany == null) {
                EmptyPaymentCard()
            } else {
                NewPaymentCard(uiState.selectedCardCompany)
            }

            Spacer(modifier = Modifier.height(40.dp))

            CardInformationInputFields(
                cardNumber = uiState.cardNumber,
                expiredDate = uiState.expiredDate,
                ownerName = uiState.ownerName,
                password = uiState.password,
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

@Preview
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen(
        uiState = NewCardUiState(),
        onCardCompanyClick = {},
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
