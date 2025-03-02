package nextstep.payments.feature.cardlist.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.payments.feature.cardlist.CardUiState
import nextstep.payments.model.Card
import nextstep.payments.view.PaymentCard

@Composable
fun CardListContent(
    cardUiState: CardUiState,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (cardUiState) {
        CardUiState.Empty -> {
            CardListNothing(
                onAddClick = onAddClick,
                modifier = modifier
            )
        }

        is CardUiState.Many -> {
            CardListWithMany(
                cards = cardUiState.cards,
                modifier = modifier
            )
        }

        is CardUiState.One -> {
            CardListWithOne(
                card = cardUiState.card,
                onAddClick = onAddClick,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun CardListNothing(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        AddNewCardInfoText()
        AddNewCardImage(
            onAddClick = onAddClick
        )
    }
}

@Composable
private fun CardListWithOne(
    card: Card,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        PaymentCard(
            card = card,
        )
        Spacer(
            modifier = Modifier.height(36.dp)
        )
        AddNewCardImage(
            onAddClick = onAddClick
        )
    }
}

@Composable
private fun CardListWithMany(
    cards: List<Card>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        items(cards) { card ->
            PaymentCard(
                card = card
            )
        }
    }
}

class CardLisContentPreviewProvider : PreviewParameterProvider<CardUiState> {
    override val values: Sequence<CardUiState> = sequenceOf(
        CardUiState.Empty,
        CardUiState.One(Card.mock),
        CardUiState.Many(listOf(Card.mock, Card.mock, Card.mock))
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListContentPreview(
    @PreviewParameter(CardLisContentPreviewProvider::class) uiState: CardUiState
) {
    CardListContent(
        cardUiState = uiState,
        onAddClick = { },
        modifier = Modifier.fillMaxSize()
    )
}