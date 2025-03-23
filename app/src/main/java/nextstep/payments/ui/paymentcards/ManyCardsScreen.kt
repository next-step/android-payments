package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.newcard.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
fun ManyCardsScreen(
    cards: List<Card>,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {},
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(cards) {
            PaymentCard(
                cardNumber = it.getFormattedCardNumber(),
                expiredDate = it.getFormattedExpiredDate(),
                ownerName = it.ownerName
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ManyCardsScreenPreview() {
    PaymentsTheme {
        ManyCardsScreen(
            cards = listOf(
                CreditCard("1111222233334444", "0421", "crew", "1234"),
                CreditCard("1111222233334444", "0421", "crew", "1234"),
                CreditCard("1111222233334444", "0421", "crew", "1234"),
                CreditCard("1111222233334444", "0421", "crew", "1234"),
                CreditCard("1111222233334444", "0421", "crew", "1234"),
            )
        )
    }
}