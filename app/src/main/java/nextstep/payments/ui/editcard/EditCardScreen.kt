package nextstep.payments.ui.editcard

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
import nextstep.payments.ui.components.PaymentToolBar
import nextstep.payments.ui.newcard.PaymentCard
import nextstep.payments.ui.transformation.CardNumberTransformation
import nextstep.payments.ui.transformation.ExpiredDateTransformation

@Composable
internal fun EditCardScreen(
    viewModel: EditCardViewModel,
    navigateToCardList: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    val editCardUiState by viewModel.editCardUiState.collectAsStateWithLifecycle()
    val cardEdited by viewModel.cardEdited.collectAsStateWithLifecycle()

    LaunchedEffect(cardEdited) {
        if (cardEdited) navigateToCardList()
    }

    EditCardScreen(
        editCardUiState = editCardUiState,
        onBackClick = onBackClick,
        onEditClick = viewModel::editCard,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword
    )
}

// Stateless
@Composable
fun EditCardScreen(
    editCardUiState: EditCardUiState,
    onBackClick: () -> Unit,
    onEditClick: () -> Unit,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            PaymentToolBar(
                title = stringResource(id = R.string.card_edit_top_bar_title),
                onBackClick = onBackClick,
                onActionsClick = onEditClick
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

            PaymentCard(bankUI = editCardUiState.bankUI)

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = editCardUiState.cardNumber,
                onValueChange = { setCardNumber(it) },
                label = { Text(stringResource(id = R.string.card_number_label)) },
                isError = editCardUiState.isCardNumberEmptyError || editCardUiState.isCardNumberFormatError,
                supportingText = {
                    if (editCardUiState.isCardNumberEmptyError) {
                        Text(text = stringResource(id = R.string.card_number_empty_error_message))
                    } else if (editCardUiState.isCardNumberFormatError) {
                        Text(text = stringResource(id = R.string.card_number_format_error_message))
                    }
                },
                placeholder = { Text(stringResource(id = R.string.card_number_place_holder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = CardNumberTransformation,
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = editCardUiState.expiredDate,
                onValueChange = { setExpiredDate(it) },
                label = { Text(stringResource(id = R.string.expired_date_label)) },
                isError = editCardUiState.isExpiredDateEmptyError || editCardUiState.isExpiredDateFormatError,
                supportingText = {
                    if (editCardUiState.isExpiredDateEmptyError) {
                        Text(text = stringResource(id = R.string.expired_date_empty_error_message))
                    } else if (editCardUiState.isExpiredDateFormatError) {
                        Text(text = stringResource(id = R.string.expired_date_format_error_message))
                    }
                },
                placeholder = { Text(stringResource(id = R.string.expired_date_place_holder)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = ExpiredDateTransformation,
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = editCardUiState.ownerName,
                onValueChange = { setOwnerName(it) },
                label = { Text(stringResource(id = R.string.owner_name_date_label)) },
                placeholder = { Text(stringResource(id = R.string.owner_name_place_holder)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = editCardUiState.password,
                onValueChange = { setPassword(it) },
                label = { Text(stringResource(id = R.string.password_name_date_label)) },
                isError = editCardUiState.isPasswordEmptyError || editCardUiState.isPasswordFormatError,
                supportingText = {
                    if (editCardUiState.isPasswordEmptyError) {
                        Text(text = stringResource(id = R.string.password_empty_error_message))
                    } else if (editCardUiState.isPasswordFormatError) {
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
private fun EditCardScreenPreview() {
//    EditCardScreen()
}
