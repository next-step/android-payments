package nextstep.payments.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.card.add.CardsScreenUiState
import nextstep.payments.card.component.AddingCardScreenTopBar
import nextstep.payments.card.component.cards.EmptyCardsContent
import nextstep.payments.card.component.cards.MultipleCardsContent
import nextstep.payments.card.component.cards.SingleCardContent

@Composable
fun CardsScreen(
    cardsViewModel: CardsViewModel,
    onAddCardClick: () -> Unit,
    onCardClick: (Card) -> Unit,
) {
    val cardsUiState by cardsViewModel.cardsScreenUiState.collectAsStateWithLifecycle()

    CardsScreen(
        cardsUiState = cardsUiState,
        onAddCardClick = onAddCardClick,
        onCardClick = onCardClick,
    )
}

@Composable
private fun CardsScreen(
    cardsUiState: CardsScreenUiState,
    onAddCardClick: () -> Unit,
    onCardClick: (Card) -> Unit,
) {
    Scaffold(
        topBar = {
            AddingCardScreenTopBar()
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (cardsUiState) {
                is CardsScreenUiState.Empty -> EmptyCardsContent(
                    modifier = Modifier.padding(top = 32.dp),
                    onAddCardClick = onAddCardClick
                )

                is CardsScreenUiState.SingleCard -> SingleCardContent(
                    card = cardsUiState.card,
                    onCardClick = { onCardClick(it) },
                    onAddCardClick = onAddCardClick
                )

                is CardsScreenUiState.MultipleCards -> MultipleCardsContent(
                    cards = cardsUiState.cards,
                    onCardClick = { onCardClick(it) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardsScreenPreview_Empty() {
    CardsScreen(
        cardsUiState = CardsScreenUiState.Empty,
        onAddCardClick = {},
        onCardClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun CardsScreenPreview_SingleCard() {
    CardsScreen(
        cardsUiState = CardsScreenUiState.SingleCard(
            card = StubCard,
        ),
        onAddCardClick = {},
        onCardClick = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun CardsScreenPreview() {
    CardsScreen(
        cardsUiState = CardsScreenUiState.MultipleCards(
            cards = listOf(StubCard, StubCard, StubCard)
        ),
        onAddCardClick = {},
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
