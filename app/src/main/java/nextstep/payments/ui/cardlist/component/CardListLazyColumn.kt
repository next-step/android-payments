package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.Card
import nextstep.payments.ui.component.PaymentCard

@Composable
fun CardListLazyColumn(
    cards: List<Card>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(vertical = 18.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        items(
            items = cards,
            key = { card -> card.cardId },
        ) {
            PaymentCard()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardsLazyColumnPreview() {
    CardListLazyColumn(
        cards = listOf(
            Card(cardId = 0L, cardNumber = "", expiredDate = "", ownerName = "", password = "")
        ),
    )
}
