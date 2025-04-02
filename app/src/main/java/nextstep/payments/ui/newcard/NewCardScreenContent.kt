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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.newcard.visualtransformation.CreditCardNumberVisualTransformation
import nextstep.payments.ui.newcard.visualtransformation.ExpirationDateVisualTransformation
import nextstep.payments.model.Card
import nextstep.payments.ui.CardInfoUiFormatter.getFormattedCardNumber
import nextstep.payments.ui.CardInfoUiFormatter.getFormattedExpiredDate
import nextstep.payments.ui.paymentcards.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewCardScreenContent(
    card: Card,
    onCardNumberChanged: (String) -> Unit,
    onExpiredDateChanged: (String) -> Unit,
    onOwnerNameChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit,
    navigateToCardList: () -> Unit,
    onSaveCard: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = navigateToCardList,
                onSaveClick = onSaveCard
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

            PaymentCard(
                cardNumber = getFormattedCardNumber(card.cardNumber),
                expiredDate = getFormattedExpiredDate(card.expiredDate),
                ownerName = card.ownerName
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = card.cardNumber,
                onValueChange = onCardNumberChanged,
                label = { Text(stringResource(R.string.new_card_card_number_label)) },
                placeholder = { Text("0000 - 0000 - 0000 - 0000") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = CreditCardNumberVisualTransformation(),
            )

            OutlinedTextField(
                value = card.expiredDate,
                onValueChange = onExpiredDateChanged,
                label = { Text(stringResource(R.string.new_card_expired_date_label)) },
                placeholder = { Text("MM / YY") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = ExpirationDateVisualTransformation(),
            )

            OutlinedTextField(
                value = card.ownerName,
                onValueChange = onOwnerNameChanged,
                label = { Text(stringResource(R.string.new_card_owner_name_label)) },
                placeholder = { Text("카드에 표시된 이름을 입력하세요.") },
                modifier = Modifier.fillMaxWidth(),
            )

            OutlinedTextField(
                value = card.password,
                onValueChange = onPasswordChanged,
                label = { Text(stringResource(R.string.new_card_password_label)) },
                placeholder = { Text("0000") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreenContent(
            card = CreditCard(
                cardNumber = "1234123412341234",
                expiredDate = "1223",
                ownerName = "유재석",
                password = "1234"
            ),
            onCardNumberChanged = {},
            onExpiredDateChanged = {},
            onOwnerNameChanged = {},
            onPasswordChanged = {},
            navigateToCardList = {},
            onSaveCard = {}
        )
    }
}