package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.CardState
import nextstep.payments.data.CardState.Card
import nextstep.payments.data.CardState.EmptyCard

@Composable
fun CardListLazyColumn(
    cards: List<CardState>,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(space = 36.dp),
        modifier = modifier
            .fillMaxSize()
            .semantics { contentDescription = "CardListLazyColumn" },
    ) {
        items(
            items = cards,
            key = { card ->
                when (card) {
                    is Card -> card.cardId
                    is EmptyCard -> EmptyCard.toString()
                }
            },
        ) { card ->
            when (card) {
                is Card -> RegisteredPaymentCard(card)
                is EmptyCard -> CardListEmptyCard(onAddCardClick = onAddCardClick)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardsLazyColumnPreview() {
    CardListLazyColumn(
        cards = emptyList(),
        onAddCardClick = { },
    )
}
