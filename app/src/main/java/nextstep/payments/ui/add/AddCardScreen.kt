package nextstep.payments.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.ui.component.CreditCardVisualTransformation
import nextstep.payments.ui.component.EmptyPaymentCard
import nextstep.payments.ui.component.ExpirationDateVisualTransformation
import nextstep.payments.ui.component.PaymentInputField

@Composable
fun AddCardScreenRoute(
    onBackClick: () -> Unit,
    onBackWithAddCompleted: (String) -> Unit,
    viewModel: AddCardViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val addCompleted by viewModel.addCompleted.collectAsState()
    LaunchedEffect(key1 = addCompleted, block = {
        if (addCompleted.isNotEmpty()) {
            onBackWithAddCompleted(addCompleted)
        }
    })
    AddCardScreen(
        uiState = uiState,
        onCardNumberChange = { viewModel.updateCardNumber(it) },
        onExpirationDateChange = { viewModel.updateExpirationDate(it) },
        onOwnerNameChange = { viewModel.updateOwnerName(it) },
        onCvcNumberChange = { viewModel.updateCvcNumber(it) },
        onPasswordChange = { viewModel.updatePassword(it) },
        onBackClick = onBackClick,
        onAddCardClick = {
            viewModel.addPayment()
        },
    )
}

@Composable
private fun AddCardScreen(
    uiState: AddCardUiState,
    onCardNumberChange: (String) -> Unit,
    onExpirationDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onCvcNumberChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBackClick: () -> Unit,
    onAddCardClick: () -> Unit,
) {

    Scaffold(
        topBar = {
            AddCardAppbar(
                onBackClick = onBackClick,
                onAddCardClick = onAddCardClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth()
                .padding(15.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            EmptyPaymentCard(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 12.dp)
            )
            PaymentInputField(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.cardNumber,
                onTextChange = { onCardNumberChange(it) },
                label = stringResource(id = R.string.card_number_label),
                hint = stringResource(id = R.string.card_number_hint),
                visualTransformation = CreditCardVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            PaymentInputField(
                text = uiState.expirationDate,
                onTextChange = { onExpirationDateChange(it) },
                label = stringResource(id = R.string.card_expiration_date_label),
                hint = stringResource(id = R.string.card_expiration_date_hint),
                visualTransformation = ExpirationDateVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            PaymentInputField(
                modifier = Modifier.fillMaxWidth(),
                text = uiState.ownerName,
                onTextChange = { onOwnerNameChange(it) },
                label = stringResource(id = R.string.card_owner_name_label),
                hint = stringResource(id = R.string.card_owner_name_hint)
            )
            PaymentInputField(
                text = uiState.cvcNumber,
                onTextChange = { onCvcNumberChange(it) },
                label = stringResource(id = R.string.card_cvc_code_label),
                hint = stringResource(id = R.string.card_cvc_code_hint),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            PaymentInputField(
                text = uiState.password,
                onTextChange = { onPasswordChange(it) },
                label = stringResource(id = R.string.card_password_label),
                hint = stringResource(id = R.string.card_password_hint),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddCardAppbar(
    onBackClick: () -> Unit,
    onAddCardClick: () -> Unit
) {
    TopAppBar(title = { Text(text = stringResource(id = R.string.add_card_title)) },
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(id = R.string.app_on_back_click)
                )
            }
        },
        actions = {
            IconButton(onClick = { onAddCardClick() }) {
                Icon(
                    imageVector = Icons.Filled.Check,
                    contentDescription = stringResource(id = R.string.add_card_title)
                )
            }
        })

}

@Preview
@Composable
private fun AddCardScreenPreview() {
    AddCardScreen(
        uiState = AddCardUiState(), {}, {}, {}, {}, {}, {}, {}
    )
}
