package nextstep.payments.screens.card.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.R
import nextstep.payments.components.card.RegisteredPaymentCard
import nextstep.payments.screens.card.list.components.AddCard
import nextstep.payments.screens.card.list.components.CardListTopBar
import nextstep.payments.screens.card.uistate.CardCompanyUiState
import nextstep.payments.screens.card.uistate.CardUiState
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(
    viewModel: CardListViewModel,
    onAddCardClick: () -> Unit,
    onCardClick: (CardUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    CardListScreen(
        state = state,
        onAddCardClick = onAddCardClick,
        onCardClick = onCardClick,
        modifier = modifier,
    )
}

@Composable
fun CardListScreen(
    state: CardListUiState,
    onAddCardClick: () -> Unit,
    onCardClick: (CardUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (state) {
        CardListUiState.Empty ->
            CardListEmptyScreen(
                onAddCardClick = onAddCardClick,
                modifier = modifier,
            )

        is CardListUiState.One ->
            CardListWithOneCardScreen(
                cardUiState = state.cardUiState,
                onAddCardClick = onAddCardClick,
                onCardClick = onCardClick,
                modifier = modifier,
            )

        is CardListUiState.Many -> {
            CardListWithManyCardScreen(
                cardUiStates = state.cardUiStates,
                onAddCardClick = onAddCardClick,
                onCardClick = onCardClick,
                modifier = modifier,
            )
        }
    }
}

@Composable
fun CardListEmptyScreen(
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { CardListTopBar() },
        containerColor = Color.White,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.card_list_empty_please_add_your_new_card),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W700),
            )
            Spacer(Modifier.height(32.dp))
            AddCard(onAddCardClick = onAddCardClick)
        }
    }
}

@Composable
fun CardListWithOneCardScreen(
    cardUiState: CardUiState,
    onAddCardClick: () -> Unit,
    onCardClick: (CardUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { CardListTopBar() },
        containerColor = Color.White,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .testTag("cards"),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(12.dp))
            RegisteredPaymentCard(cardUiState = cardUiState, onClick = { onCardClick(cardUiState) })
            Spacer(Modifier.height(36.dp))
            AddCard(onAddCardClick = onAddCardClick)
        }
    }
}

@Composable
fun CardListWithManyCardScreen(
    cardUiStates: List<CardUiState>,
    onAddCardClick: () -> Unit,
    onCardClick: (CardUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { CardListTopBar(onAddClick = onAddCardClick) },
        containerColor = Color.White,
    ) { paddingValues ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(36.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 12.dp)
        ) {
            items(items = cardUiStates, key = { it.cardNumber }) { cardUiState ->
                RegisteredPaymentCard(cardUiState, onClick = { onCardClick(cardUiState) })
            }
        }
    }
}


@Preview(name = "카드 목록에 카드가 하나도 없을 때")
@Composable
private fun Preview1() {
    PaymentsTheme {
        CardListEmptyScreen(onAddCardClick = {})
    }
}

@Preview(name = "카드 목록에 카드가 하나 있을 때")
@Composable
private fun Preview2() {
    PaymentsTheme {
        val card = CardUiState(
            id = 0,
            cardNumber = "0000000000000000",
            expiredDate = "0000",
            ownerName = "CREW",
            password = "0000",
            selectedCardCompany = CardCompanyUiState.BC,
        )
        CardListWithOneCardScreen(
            cardUiState = card,
            onAddCardClick = {},
            onCardClick = {},
        )
    }
}

@Preview(name = "카드 목록에 카드가 두개 이상 있을 때")
@Composable
private fun Preview3() {
    PaymentsTheme {
        val cards = listOf(
            CardUiState(
                id = 0,
                cardNumber = "1111222200000000",
                expiredDate = "0522",
                ownerName = "CREW",
                password = "0000",
                selectedCardCompany = CardCompanyUiState.KB,
            ),
            CardUiState(
                id = 1,
                cardNumber = "0000000000000000",
                expiredDate = "0421",
                ownerName = "BANDAL",
                password = "0000",
                selectedCardCompany = CardCompanyUiState.HYUNDAI,
            ),
        )
        CardListWithManyCardScreen(
            cardUiStates = cards,
            onAddCardClick = {},
            onCardClick = {},
        )
    }
}

