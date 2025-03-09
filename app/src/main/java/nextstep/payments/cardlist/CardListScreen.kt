package nextstep.payments.cardlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import nextstep.payments.cardlist.component.CardListCardsContent
import nextstep.payments.cardlist.component.CardListNoCardContent
import nextstep.payments.cardlist.component.CardListOneCardContent
import nextstep.payments.cardlist.component.CardListTopBar
import nextstep.payments.model.Card
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(
    viewModel: CardListViewModel,
    navigateToNewCardScreen: () -> Unit,
    navigateToEditScreen: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collectLatest {
            when (it) {
                is CardListSideEffect.NavigateToNewCardScreen -> navigateToNewCardScreen()
                is CardListSideEffect.NavigateToEditScreen -> navigateToEditScreen(it.cardId)
            }
        }
    }

    CardListScreen(
        cardsState = state.value.cardsState,
        sendEvent = viewModel::sendEvent,
        modifier = modifier,
    )
}

@Composable
fun CardListScreen(
    cardsState: CardsState,
    sendEvent: (CardListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                isShowAddButton = cardsState is CardsState.Cards,
                sendEvent = sendEvent,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .testTag("CardListTopBar"),
            )
        },
        modifier = modifier,
    ) { paddingValue ->
        when (cardsState) {
            is CardsState.NoCard -> {
                CardListNoCardContent(
                    sendEvent = sendEvent,
                    modifier = Modifier
                        .padding(paddingValue)
                        .fillMaxSize()
                )
            }

            is CardsState.OneCard -> {
                CardListOneCardContent(
                    cardsStateState = cardsState,
                    sendEvent = sendEvent,
                    modifier = Modifier
                        .padding(paddingValue)
                        .fillMaxSize(),
                )
            }

            is CardsState.Cards -> {
                CardListCardsContent(
                    cardsStateState = cardsState,
                    sendEvent = sendEvent,
                    modifier = Modifier
                        .padding(paddingValue)
                        .fillMaxSize(),
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
            cardsState = CardsState.NoCard,
            sendEvent = {},
        )
    }
}

@Preview
@Composable
private fun CardListScreenOneCardPreview() {
    PaymentsTheme {
        CardListScreen(
            cardsState = CardsState.OneCard(
                Card(
                    id = 1,
                    cardNumber = "1234-5678-9012-3451",
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
            cardsState = CardsState.Cards(
                listOf(
                    Card(
                        id = 1,
                        cardNumber = "1234-5678-9012-3452",
                        expiredDate = "12/34",
                        ownerName = "홍길동",
                        password = "1234",
                    ),
                    Card(
                        id = 2,
                        cardNumber = "1234-5678-9012-3455",
                        expiredDate = "12/34",
                        ownerName = "홍길동",
                        password = "1234",
                    ),
                )
            ),
            sendEvent = {},
        )
    }
}
