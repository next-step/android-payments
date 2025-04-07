package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.Card
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.CardInfoUiFormatter.getFormattedCardNumber
import nextstep.payments.ui.CardInfoUiFormatter.getFormattedExpiredDate
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
fun ManyCardsScreen(
    cards: List<Card>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(
            items = cards,
            key = { it.cardNumber }
        ) {
            PaymentCard(
                cardNumber = getFormattedCardNumber(it.cardNumber),
                expiredDate = getFormattedExpiredDate(it.expiredDate),
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
                CreditCard("2222333344445555", "0421", "crew", "1234"),
                CreditCard("3333444455556666", "0421", "crew", "1234"),
                CreditCard("7777888899990000", "0421", "crew", "1234"),
                CreditCard("1234567812345678", "0421", "crew", "1234"),
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}