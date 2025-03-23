package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.newcard.AddCard
import nextstep.payments.ui.newcard.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
fun OneCardScreen(
    card: Card,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PaymentCard(
            cardNumber = card.getFormattedCardNumber(),
            expiredDate = card.getFormattedExpiredDate(),
            ownerName = card.ownerName
        )
        AddCard(onClick = onAddClick)
    }
}

@Preview
@Composable
private fun OneCardScreenPreview() {
    PaymentsTheme {
        OneCardScreen(
            card = CreditCard("1111222233334444", "0421", "crew", "1234")
        )
    }
}