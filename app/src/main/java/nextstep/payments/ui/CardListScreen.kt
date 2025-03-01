package nextstep.payments.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.currentStateAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.data.model.Card
import nextstep.payments.ui.component.Card
import nextstep.payments.ui.component.CardAdd
import nextstep.payments.ui.component.CardAddAffordance
import nextstep.payments.ui.component.CardListTopBar
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.utils.toCardAdd
import nextstep.payments.viewmodel.CardListViewModel
import nextstep.payments.viewmodel.CardsUiState

@Composable
fun CardListScreen(viewModel: CardListViewModel = viewModel()) {

    val lifecycleOwner = LocalLifecycleOwner.current
    val currentLifecycleState by lifecycleOwner.lifecycle.currentStateAsState()

    if (currentLifecycleState == Lifecycle.State.RESUMED) viewModel.fetchCards()

    val cardsUiState by viewModel.cardsUiState.collectAsStateWithLifecycle()

    CardListScreen(cardsUiState)
}

@Composable
fun CardListScreen(cardsUiState: CardsUiState) {
    Scaffold(topBar = {
        CardListTopBar(cardsUiState)
    }) { innerPadding ->
        CardList(
            cardsUiState = cardsUiState, modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        )
    }
}

@Composable
private fun CardListTopBar(cardsUiState: CardsUiState) {
    val context = LocalContext.current

    if (cardsUiState is CardsUiState.Many) {
        CardListTopBar(onAddClick = { context.toCardAdd() })
    } else {
        CardListTopBar()
    }
}

@Composable
private fun CardList(
    cardsUiState: CardsUiState, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter,
    ) {
        when (cardsUiState) {
            is CardsUiState.Empty -> EmptyCardList()
            is CardsUiState.One -> OneCardList(card = cardsUiState.card)
            is CardsUiState.Many -> ManyCardList(cards = cardsUiState.cards)
        }
    }
}

@Composable
private fun ParentCardList(modifier: Modifier, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = modifier.padding(vertical = verticalPadding),
        verticalArrangement = Arrangement.spacedBy(verticalPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        content()
    }
}

@Composable
private fun EmptyCardList(modifier: Modifier = Modifier) {
    ParentCardList(modifier) {
        CardAddAffordance()
        CardAdd()
    }
}

@Composable
private fun OneCardList(
    card: Card, modifier: Modifier = Modifier
) {
    ParentCardList(modifier) {
        Card(card)
        CardAdd()
    }
}

@Composable
private fun ManyCardList(
    cards: List<Card>, modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(verticalPadding),
        contentPadding = PaddingValues(top = verticalPadding)
    ) {

        items(items = cards, key = { it -> it.number + it.updated }) { card ->
            Card(card)
        }
    }
}

private val verticalPadding = 32.dp

private val defaultCard = Card(
    number = "1111 - 1111 - **** - ****",
    ownerName = "홍길동",
    expiredDate = "10/04",
    password = "1111",
    company = null,
)

@Preview(name = "카드 0 개일 경우 목록")
@Composable
private fun CardListScreenPreview() {
    PaymentsTheme {
        CardListScreen(CardsUiState.Empty)
    }
}


@Preview(name = "카드 1 개일 경우 목록")
@Composable
private fun CardListScreenPreview1() {
    PaymentsTheme {
        CardListScreen(CardsUiState.One(card = defaultCard))
    }
}

@Preview(name = "카드 N 개일 경우 목록")
@Composable
private fun CardListScreenPreview2() {
    val cards = listOf(
        defaultCard,
        defaultCard.copy(ownerName = "홍길동1"),
        defaultCard.copy(ownerName = "홍길동2"),
        defaultCard.copy(ownerName = "홍길동3"),
        defaultCard.copy(ownerName = "홍길동4"),
        defaultCard.copy(ownerName = "홍길동5"),
        defaultCard.copy(ownerName = "홍길동6"),
        defaultCard.copy(ownerName = "홍길동7"),
    ).toList()

    PaymentsTheme {
        CardListScreen(CardsUiState.Many(cards = cards))
    }
}