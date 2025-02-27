package nextstep.payments.ui.cardlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.cardlist.component.CardListTopBar
import nextstep.payments.ui.cardlist.component.EmptyCardContainer
import nextstep.payments.ui.cardlist.component.ManyCardContainer
import nextstep.payments.ui.cardlist.component.OneCardContainer
import java.time.YearMonth

@Composable
fun CardListScreen(
    uiState: CardListUiState,
    onRouteToNewCard: () -> Unit,
    onRouteToUpdateCard: (Card) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                onAddClick = onRouteToNewCard,
                isShowAddText = uiState is CardListUiState.Many
            )
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValue ->
        when (uiState) {
            CardListUiState.Empty -> {
                EmptyCardContainer(
                    onRouteToNewCard = onRouteToNewCard,
                    modifier = modifier.padding(paddingValue)
                )
            }

            is CardListUiState.One -> {
                OneCardContainer(
                    item = uiState.card,
                    onItemClick = onRouteToUpdateCard,
                    onRouteToNewCard = onRouteToNewCard,
                    modifier = modifier.padding(paddingValue)
                )
            }

            is CardListUiState.Many -> {
                ManyCardContainer(
                    items = uiState.cards,
                    onItemClick = onRouteToUpdateCard,
                    modifier = modifier.padding(paddingValue)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CardListScreenUiStateEmptyPreview() {
    PaymentsTheme {
        CardListScreen(
            uiState = CardListUiState.Empty,
            onRouteToNewCard = {},
            onRouteToUpdateCard = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CardListScreenUiStateOnePreview() {
    val card = Card(
        type = BankType.HANA,
        number = "1234123412341234",
        expiredDate = YearMonth.of(21, 4),
        ownerName = "홍길동",
        password = "1234"
    )
    PaymentsTheme {
        CardListScreen(
            uiState = CardListUiState.One(card),
            onRouteToNewCard = {},
            onRouteToUpdateCard = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CardListScreenUiStateManyPreview() {

    val cards = MutableList(4) {
        Card(
            type = BankType.HANA,
            number = "1234123412341234",
            expiredDate = YearMonth.of(21, 4),
            ownerName = "홍길동",
            password = "1234"
        )
        Card(
            type = BankType.KAKAO,
            number = "2345234523452345",
            expiredDate = YearMonth.of(22, 4),
            ownerName = "홍길동",
            password = "1234"
        )
        Card(
            type = BankType.KB,
            number = "3456345634563456",
            expiredDate = YearMonth.of(23, 4),
            ownerName = "홍길동",
            password = "1234"
        )
        Card(
            type = BankType.HYUNDAI,
            number = "4567456745674567",
            expiredDate = YearMonth.of(24, 4),
            ownerName = "홍길동",
            password = "1234"
        )
    }

    PaymentsTheme {
        CardListScreen(
            uiState = CardListUiState.Many(cards),
            onRouteToNewCard = {},
            onRouteToUpdateCard = {}
        )
    }
}