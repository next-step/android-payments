package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.newcard.AddCard
import nextstep.payments.ui.newcard.PaymentCard
import nextstep.payments.ui.newcard.PaymentCardsTopBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCardsScreen(
    cards: List<Card>,
    modifier: Modifier = Modifier,
    onAddClick: () -> Unit = {}
) {
    Scaffold(
        topBar = { PaymentCardsTopBar() },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(36.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(cards.size + 1) { index ->
                if (cards.isEmpty()) {
                    Text(
                        modifier = Modifier
                            .padding(vertical = 32.dp),
                        text = stringResource(R.string.add_new_card_label),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                if (index == cards.size) {
                    AddCard(onClick = onAddClick)
                    return@items
                }
                PaymentCard(
                    cardNumber = cards[index].getFormattedCardNumber(),
                    expiredDate = cards[index].getFormattedExpiredDate(),
                    ownerName = cards[index].ownerName
                )
            }
        }
    }
}

@Preview
@Composable
private fun PaymentCardsScreenPreview() {
    PaymentsTheme {
        PaymentCardsScreen(
            cards = listOf(
                CreditCard("1111222233334444", "0421", "crew", "1234"),
                CreditCard("1111222233334444", "0421", "crew", "1234"),
                CreditCard("1111222233334444", "0421", "crew", "1234"),
                CreditCard("1111222233334444", "0421", "crew", "1234"),
            )
        )
    }
}

@Preview
@Composable
private fun NoCardsScreenPreview() {
    PaymentsTheme {
        PaymentCardsScreen(cards = listOf())
    }
}