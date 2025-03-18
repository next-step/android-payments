package nextstep.payments.ui.creditcard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.model.CreditCardType.CardInfo
import nextstep.payments.ui.newcard.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
fun CreditCardMany(
    cards: List<CardInfo>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PaymentCards(cards = cards, modifier = Modifier.padding(vertical = 12.dp))
    }
}

@Composable
private fun PaymentCards(cards: List<CardInfo>, modifier: Modifier = Modifier) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(36.dp), modifier = modifier) {
        items(cards, key = { it.number }) {
            PaymentCard(creditCardType = it, Modifier.padding(horizontal = 76.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        PaymentCards(
            cards = listOf(
                CardInfo(
                    "2234567812345678",
                    "0421",
                    "김무현",
                    "1234",
                    cardName = "신한카드"
                ),
                CardInfo(
                    "3234567812345678",
                    "0421",
                    "김무현",
                    "1234",
                    cardName = "신한카드"
                ),
                CardInfo(
                    "4234567812345678",
                    "0421",
                    "김무현",
                    "1234",
                    cardName = "신한카드"
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CreditCardManyPreview() {
    PaymentsTheme {
        CreditCardMany(
            List(8) { it ->
                CardInfo(
                    "123456781234567$it", "0421", "김무현", "1234",
                    cardName = "신한카드"
                )
            }
        )
    }
}
