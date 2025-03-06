package nextstep.payments.list.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.common.component.PaymentCard
import nextstep.payments.common.model.Card

@Composable
fun ManyCardsScreen(
    cards: List<Card>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(cards) { card ->
            PaymentCard(
                card = card
            )
            Spacer(modifier = Modifier.height(36.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ManyCardsScreenPreview() {
    ManyCardsScreen(
        cards = List(5) {
            Card(
                cardNumber = "1111 - 2222 - **** - ****",
                expiredDate = "12/25",
                ownerName = "CREW",
                password = "1234",
            )
        },
    )
}
