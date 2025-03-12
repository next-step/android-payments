package nextstep.payments.ui.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.model.IssuingBank
import nextstep.payments.ui.components.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCardFormScreen(
    formState: PaymentCardFormState,
    snackBarHostState: SnackbarHostState,
    onPaymentCardClick: () -> Unit,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit,
) {
    Scaffold(
        topBar = {
            topBar()
        },
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentCard(
                cardNumber = formState.cardNumber,
                expiredDate = formState.expiredDate,
                ownerName = formState.ownerName,
                issuingBank = formState.issuingBank,
                onClick = onPaymentCardClick,
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = formState.cardNumber,
                onValueChange = formState.onCardNumberChanged,
                singleLine = true,
                label = { Text(stringResource(R.string.new_card_card_number_label)) },
                placeholder = { Text(stringResource(R.string.new_card_card_number_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = formState.expiredDate,
                onValueChange = formState.onExpiredDateChanged,
                label = { Text(stringResource(R.string.new_card_expired_date_label)) },
                singleLine = true,
                placeholder = { Text(stringResource(R.string.new_card_expired_date_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = formState.ownerName,
                onValueChange = formState.onOwnerNameChanged,
                singleLine = true,
                label = { Text(stringResource(R.string.new_card_owner_name_label)) },
                placeholder = { Text(stringResource(R.string.new_card_owner_name_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = formState.password,
                onValueChange = formState.onPasswordChanged,
                singleLine = true,
                label = { Text(stringResource(R.string.new_card_password_label)) },
                placeholder = { Text(stringResource(R.string.new_card_password_placeholder)) },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Preview
@Composable
private fun NewCardScreenPreview() {
    PaymentsTheme {
        PaymentCardFormScreen(
            formState = PaymentCardFormState(
                cardNumber = "1234 - 5678 - 1234 - 5678",
                expiredDate = "12 / 34",
                ownerName = "홍길동",
                password = "1234",
                issuingBank = IssuingBank.SHINHAN_CARD,
                onCardNumberChanged = {},
                onExpiredDateChanged = {},
                onOwnerNameChanged = {},
                onPasswordChanged = {},
            ),
            snackBarHostState = SnackbarHostState(),
            topBar = {},
            onPaymentCardClick = {}
        )
    }
}
