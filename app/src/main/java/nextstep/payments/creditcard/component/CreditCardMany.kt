package nextstep.payments.creditcard.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.model.CreditCard
import nextstep.payments.newcard.component.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditCardMany(
    onNavigateToNewCard: () -> Unit,
    cards: List<CreditCard>,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        CenterAlignedTopAppBar(
            title = { Text("Payments", fontSize = 22.sp) },
            actions = {
                Text(
                    "추가",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable { onNavigateToNewCard() }
                        .padding(end = 16.dp),
                    color = Color.Black
                )
            }
        )
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
            {},
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
