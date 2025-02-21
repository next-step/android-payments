package nextstep.payments.ui.cardlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.designsystem.component.PaymentCard
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.designsystem.theme.TextColor
import nextstep.payments.model.Card
import nextstep.payments.ui.cardlist.component.AddCardButton
import nextstep.payments.ui.cardlist.component.CardListTopBar

@Composable
fun CardListScreen(
    uiState: CardListUiState,
    onRouteToNewCard: () -> Unit,
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp)
        ) {
            when (uiState) {
                CardListUiState.Empty -> {
                    item {
                        Text(
                            stringResource(R.string.content_add_card),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextColor,
                            modifier = Modifier.padding(vertical = 32.dp)
                        )
                        AddCardButton(onAddClick = onRouteToNewCard)
                    }
                }

                is CardListUiState.One -> {
                    item { PaymentCard(item = uiState.card, onItemClick = {}) }
                    item { AddCardButton(onAddClick = onRouteToNewCard) }
                }

                is CardListUiState.Many -> {
                    items(uiState.cards) { item ->
                        PaymentCard(item = item, onItemClick = {})
                    }
                }
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
            onRouteToNewCard = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CardListScreenUiStateOnePreview() {
    val card = Card(
        number = "1234123412341234",
        expiredDate = "0421",
        ownerName = "홍길동",
        password = "1234"
    )
    PaymentsTheme {
        CardListScreen(
            uiState = CardListUiState.One(card),
            onRouteToNewCard = {}
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun CardListScreenUiStateManyPreview() {

    val cards = MutableList(4) {
        Card(
            number = "1234123412341234",
            expiredDate = "0421",
            ownerName = "홍길동",
            password = "1234"
        )
        Card(
            number = "2345234523452345",
            expiredDate = "0422",
            ownerName = "홍길동",
            password = "1234"
        )
        Card(
            number = "3456345634563456",
            expiredDate = "0423",
            ownerName = "홍길동",
            password = "1234"
        )
        Card(
            number = "4567456745674567",
            expiredDate = "0424",
            ownerName = "홍길동",
            password = "1234"
        )
    }

    PaymentsTheme {
        CardListScreen(
            uiState = CardListUiState.Many(cards),
            onRouteToNewCard = {}
        )
    }
}