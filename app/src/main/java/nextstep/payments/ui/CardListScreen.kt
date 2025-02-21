package nextstep.payments.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.model.CardModel
import nextstep.payments.ui.component.Card
import nextstep.payments.ui.component.CardAdd
import nextstep.payments.ui.component.CardAddAffordance
import nextstep.payments.ui.component.CardListTopBar
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.utils.toCardAdd
import nextstep.payments.viewmodel.CardsUiState

@Composable
fun CardListScreen(
    cardsUiState: CardsUiState,
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CardListTopBar(
                onAddClick = { context.toCardAdd() }.takeIf { cardsUiState is CardsUiState.Many }
            )
        }
    ) { innerPadding ->
        CardList(
            cardsUiState = cardsUiState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun CardList(
    cardsUiState: CardsUiState,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(32.dp),
            contentPadding = PaddingValues(top = 32.dp)
        ) {

            when (cardsUiState) {
                is CardsUiState.Empty -> {
                    item { CardAddAffordance() }
                    item { CardAdd() }
                }

                is CardsUiState.One -> {
                    item { Card(cardsUiState.card) }
                    item { CardAdd() }
                }

                is CardsUiState.Many -> {
                    items(
                        items = cardsUiState.cards,
                        key = { it -> it.number }
                    ) { card ->
                        Card(card)
                    }
                }
            }
        }
    }
}

private val defaultCard = CardModel(
    number = "1111 - 1111 - **** - ****",
    ownerName = "홍길동",
    expiredDate = "10/04",
    password = "1111"
)

@Preview(name = "카드 0 개일 경우 목록")
@Composable
private fun CardListScreenPreview() {
    PaymentsTheme {
        CardListScreen(
            cardsUiState = CardsUiState.Empty,
        )
    }
}


@Preview(name = "카드 1 개일 경우 목록")
@Composable
private fun CardListScreenPreview1() {
    PaymentsTheme {
        CardListScreen(
            cardsUiState = CardsUiState.One(defaultCard),
        )
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
    )
    PaymentsTheme {
        CardListScreen(
            cardsUiState = CardsUiState.Many(cards = cards),
        )
    }
}