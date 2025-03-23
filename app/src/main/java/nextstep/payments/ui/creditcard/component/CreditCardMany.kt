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
import nextstep.payments.R
import nextstep.payments.ui.model.CreditCardType.RegisteredCard
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.newcard.model.CardCompany
import nextstep.payments.ui.theme.PaymentsTheme


@Composable
fun CreditCardMany(
    cards: List<RegisteredCard>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        PaymentCards(cards = cards, modifier = Modifier.padding(vertical = 12.dp))
    }
}

@Composable
private fun PaymentCards(cards: List<RegisteredCard>, modifier: Modifier = Modifier) {
    LazyColumn(verticalArrangement = Arrangement.spacedBy(36.dp), modifier = modifier) {
        items(cards) {
            PaymentCard(
                creditCardType = it,
                modifier = Modifier.padding(horizontal = 76.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        PaymentCards(
            cards = listOf(
                RegisteredCard(
                    "2234567812345678",
                    "0421",
                    "김무현",
                    "1234",
                    CardCompany(R.drawable.ic_kakao, "카카오뱅크", 0xFF441444)
                ),
                RegisteredCard(
                    "3234567812345678",
                    "0421",
                    "김무현",
                    "1234",
                    CardCompany(R.drawable.ic_kakao, "카카오뱅크", 0xFF444144)
                ),
                RegisteredCard(
                    "4234567812345678",
                    "0421",
                    "김무현",
                    "1234",
                    CardCompany(R.drawable.ic_kakao, "카카오뱅크", 0xFF442444)
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
                RegisteredCard(
                    "123456781234567$it", "0421", "김무현", "1234",
                    CardCompany(R.drawable.ic_kakao, "카카오뱅크", 0xF444444)
                )
            }
        )
    }
}
