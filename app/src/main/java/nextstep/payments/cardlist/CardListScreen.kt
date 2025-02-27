package nextstep.payments.cardlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.cardlist.component.CardListContent
import nextstep.payments.cardlist.component.CardListNoCardContent
import nextstep.payments.cardlist.component.CardListTopBar
import nextstep.payments.model.Card
import nextstep.payments.parameters.CardCountPreviewParameter
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(
    viewModel: CardListViewModel,
    modifier: Modifier = Modifier
) {
    val state = viewModel.state.collectAsStateWithLifecycle()
    CardListScreen(
        cards = state.value.cards,
        cardCount = state.value.currentCardCount,
        modifier = modifier,
    )
}

@Composable
fun CardListScreen(
    cards: List<Card>,
    cardCount: CardCount,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { CardListTopBar(cardCount == CardCount.CARDS) },
        modifier = modifier,
    ) { paddingValue ->
        when (cardCount) {
            CardCount.NO_CARD -> {
                CardListNoCardContent(
                    modifier = Modifier
                        .padding(paddingValue)
                        .fillMaxSize()
                )
            }

            CardCount.ONE_CARD -> {
                CardListContent(
                    cardList = cards,
                    cardCountState = CardCount.ONE_CARD,
                    modifier = Modifier
                        .padding(paddingValue)
                        .fillMaxSize(),
                )
            }

            else -> {
                CardListContent(
                    cardList = cards,
                    cardCountState = CardCount.CARDS,
                    modifier = Modifier
                        .padding(paddingValue)
                        .fillMaxSize()
                )
            }
        }
    }
}

@Preview
@Composable
private fun CardListScreenPreview(
    @PreviewParameter(CardCountPreviewParameter::class) cardCount: CardCount,
) {
    PaymentsTheme {
        CardListScreen(
            cardCount = cardCount,
            cards = emptyList(),
        )
    }
}
