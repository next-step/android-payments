package nextstep.payments.cardlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import nextstep.payments.cardlist.component.CardListContent
import nextstep.payments.cardlist.component.CardListNoCardContent
import nextstep.payments.cardlist.component.CardListTopBar
import nextstep.payments.model.Card
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(
    viewModel: CardListViewModel,
    navigateToNewCardScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is CardListSideEffect.NavigateToNewCardScreen -> navigateToNewCardScreen()
            }
        }
    }

    CardListScreen(
        cards = state.value.cards,
        cardCount = state.value.currentCardCount,
        sendEvent = viewModel::sendEvent,
        modifier = modifier,
    )
}

@Composable
fun CardListScreen(
    cards: List<Card>,
    cardCount: CardCount,
    sendEvent: (CardListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                isShowAddButton = cardCount == CardCount.CARDS,
                sendEvent = sendEvent,
                modifier = Modifier.padding(horizontal = 20.dp),
            )
        },
        modifier = modifier,
    ) { paddingValue ->
        when (cardCount) {
            CardCount.NO_CARD -> {
                CardListNoCardContent(
                    sendEvent = sendEvent,
                    modifier = Modifier
                        .padding(paddingValue)
                        .fillMaxSize()
                )
            }

            CardCount.ONE_CARD -> {
                CardListContent(
                    cardList = cards,
                    cardCountState = CardCount.ONE_CARD,
                    sendEvent = sendEvent,
                    modifier = Modifier
                        .padding(paddingValue)
                        .fillMaxSize(),
                )
            }

            else -> {
                CardListContent(
                    cardList = cards,
                    cardCountState = CardCount.CARDS,
                    sendEvent = sendEvent,
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
private fun CardListScreenNoCardPreview() {
    PaymentsTheme {
        CardListScreen(
            cardCount = CardCount.NO_CARD,
            cards = emptyList(),
            sendEvent = {},
        )
    }
}

@Preview
@Composable
private fun CardListScreenOneCardPreview() {
    PaymentsTheme {
        CardListScreen(
            cardCount = CardCount.ONE_CARD,
            cards = listOf(
                Card(
                    id = 1,
                    cardNumber = "1234-5678-9012-3456",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                )
            ),
            sendEvent = {},
        )
    }
}

@Preview
@Composable
private fun CardListScreenCardsPreview() {
    PaymentsTheme {
        CardListScreen(
            cardCount = CardCount.CARDS,
            cards = listOf(
                Card(
                    id = 1,
                    cardNumber = "1234-5678-9012-3456",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                ),
                Card(
                    id = 2,
                    cardNumber = "1234-5678-9012-3456",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                ),
                Card(
                    id = 3,
                    cardNumber = "1234-5678-9012-3456",
                    expiredDate = "12/34",
                    ownerName = "홍길동",
                    password = "1234",
                ),
            ),
            sendEvent = {},
        )
    }
}
