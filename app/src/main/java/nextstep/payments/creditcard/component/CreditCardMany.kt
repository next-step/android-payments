package nextstep.payments.creditcard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.CreditCard
import nextstep.payments.newcard.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
fun CreditCardMany(
    cards: List<CreditCard>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PaymentCards(cards = cards, modifier = Modifier.padding(vertical = 12.dp))
    }
}

@Composable
private fun PaymentCards(cards: List<CreditCard>, modifier: Modifier = Modifier) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(36.dp), modifier = modifier) {
        items(cards, key = { it.number }) {
            PaymentCard(creditCard = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        PaymentCards(
            cards = listOf(
                CreditCard(
                    "2234567812345678",
                    "0421",
                    "김무현",
                    "1234",
                ),
                CreditCard(
                    "3234567812345678",
                    "0421",
                    "김무현",
                    "1234"
                ),
                CreditCard("4234567812345678", "0421", "김무현", "1234")
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CreditCardManyPreview() {
    PaymentsTheme {
        CreditCardMany(
            listOf(
                CreditCard("1234567812345678", "0421", "김무현", "1234"),
                CreditCard("2234567812345678", "0421", "김무현", "1234"),
                CreditCard("3234567812345678", "0421", "김무현", "1234"),
                CreditCard("4234567812345678", "0421", "김무현", "1234"),
                CreditCard("5234567812345678", "0421", "김무현", "1234"),
                CreditCard("6234567812345678", "0421", "김무현", "1234"),
                CreditCard("7234567812345678", "0421", "김무현", "1234"),
                CreditCard("8234567812345678", "0421", "김무현", "1234")
            )
        )
    }
}
