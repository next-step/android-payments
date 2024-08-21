package nextstep.payments.ui.newcard

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.model.OwnerNameValidResult
import nextstep.payments.ui.component.CardNumberVisualTransformation
import nextstep.payments.ui.component.ExpiredDateVisualTransformation
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun NewCardRoute(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NewCardScreen(
        uiState = uiState,
        onCardNumberChange = viewModel::setCardNumber,
        onExpiredDateChange = viewModel::setExpiredDate,
        onOwnerNameChange = viewModel::setOwnerName,
        onPasswordChange = viewModel::setPassword,
        modifier = modifier,
    )
}

@Composable
internal fun NewCardScreen(
    uiState: NewCardScreenUiState,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = { TODO() },
                onSaveClick = { TODO() },
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier =
                Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp),
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentCard()

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = uiState.cardNumber,
                onValueChange = onCardNumberChange,
                label = { Text(stringResource(id = R.string.label_card_number)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_card_number)) },
                visualTransformation = CardNumberVisualTransformation(),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("cardNumber"),
            )

            OutlinedTextField(
                value = uiState.expiredDate,
                onValueChange = onExpiredDateChange,
                label = { Text(stringResource(id = R.string.label_expired_date)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_expired_date)) },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("expiredDate"),
            )

            OutlinedTextField(
                value = uiState.ownerName,
                onValueChange = onOwnerNameChange,
                label = { Text(stringResource(id = R.string.label_owner_name)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_owner_name)) },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("ownerName"),
            )

            OutlinedTextField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                label = { Text(stringResource(id = R.string.label_passwrod)) },
                placeholder = { Text(stringResource(id = R.string.placeholder_password)) },
                visualTransformation = PasswordVisualTransformation(),
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .testTag("password"),
            )
        }
    }
}

@Preview
@Composable
private fun NewCardScreenPreview(
    @PreviewParameter(NewCardScreenProvider::class) uiState: NewCardScreenUiState,
) {
    PaymentsTheme {
        NewCardScreen(
            uiState = uiState,
            onCardNumberChange = {},
            onExpiredDateChange = {},
            onOwnerNameChange = {},
            onPasswordChange = {},
        )
    }
}

private class NewCardScreenProvider : PreviewParameterProvider<NewCardScreenUiState> {
    override val values: Sequence<NewCardScreenUiState> =
        sequenceOf(
            NewCardScreenUiState(
                cardNumber = "1234567812345678",
                expiredDate = "1234",
                ownerName = "홍길동",
                password = "1234",
                ownerNameValidResult = OwnerNameValidResult.VALID,
            ),
            NewCardScreenUiState(
                cardNumber = "1234567812345678",
                expiredDate = "1234",
                ownerName = "홍길동",
                password = "1234567890123456789012345678901",
                ownerNameValidResult = OwnerNameValidResult.ERROR_OWNER_NAME_LENGTH,
            ),
        )
}
