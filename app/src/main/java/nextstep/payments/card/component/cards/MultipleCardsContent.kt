package nextstep.payments.card.component.cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.card.Card
import nextstep.payments.card.component.PaymentCard

@Composable
fun MultipleCardsContent(
    cards: List<Card>,
    onCardClick: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        items(cards) { card ->
            PaymentCard(
                modifier = Modifier.clickable(onClick = { onCardClick(card) }),
                cardColor = Color(0xFF333333),
                card = card,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MultipleCardsContentPreview() {
    MultipleCardsContent(
        cards = listOf(StubCard, StubCard, StubCard),
        onCardClick = {},
    )
}

private val StubCard = Card(
    cardNumber = "1111 - 2222 - 3333 - 4444",
    expireDate = "04/21",
    ownerName = "Crew",
    cvcNumber = "000",
    password = "1234",
)
