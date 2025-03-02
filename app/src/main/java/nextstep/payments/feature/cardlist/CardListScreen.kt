package nextstep.payments.feature.cardlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.model.Card
import nextstep.payments.view.AddNewCardImage
import nextstep.payments.view.AddNewCardInfoText
import nextstep.payments.view.CardListTopBar
import nextstep.payments.view.PaymentCard

@Composable
fun CardListScreen(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardListViewModel = viewModel()
) {
    val cardUiState by viewModel.cardsUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CardListTopBar(
                onAddClick = onAddClick
            )
        },
        modifier = modifier
    ) { innerPadding ->
        CardListContent(
            cardUiState = cardUiState,
            onAddClick = onAddClick,
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun CardListContent(
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
    ) {
        Spacer(
            modifier = Modifier.height(32.dp)
        )
        AddNewCardInfoText()
        Spacer(
            modifier = Modifier.height(32.dp)
        )
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