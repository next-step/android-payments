package nextstep.payments.ui.paymentcards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.CreditCard
import nextstep.payments.ui.newcard.AddCard
import nextstep.payments.ui.newcard.PaymentCard
import nextstep.payments.ui.newcard.PaymentCardsTopBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCardsScreen(
    cards: List<Card>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { PaymentCardsTopBar() },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(cards.size + 1) { index ->
                if (index == cards.size) {
                    AddCard()
                    return@items
                }
                PaymentCard()
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
                CreditCard("","","",""),
                CreditCard("","","",""),
                CreditCard("","","",""),
                CreditCard("","","","")
            )
        )
    }
}