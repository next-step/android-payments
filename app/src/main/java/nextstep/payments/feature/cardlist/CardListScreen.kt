package nextstep.payments.feature.cardlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.feature.cardlist.view.CardListContent
import nextstep.payments.feature.cardlist.view.CardListTopBar
import nextstep.payments.model.Card

@Composable
fun CardListScreen(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardListViewModel = viewModel()
) {
    val cardUiState by viewModel.cardsUiState.collectAsStateWithLifecycle()

    CardListScreen(
        cardUiState = cardUiState,
        onAddClick = onAddClick,
        modifier = modifier
    )
}

@Composable
fun CardListScreen(
    cardUiState: CardUiState,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                showAddButton = cardUiState is CardUiState.Many,
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

@Preview
@Composable
private fun CardListScreenPreview() {
    CardListScreen(
        cardUiState = CardUiState.Many(listOf(Card.mock, Card.mock, Card.mock)),
        onAddClick = {}
    )
}