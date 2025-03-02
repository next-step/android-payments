package nextstep.payments.feature.cardlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.model.Card
import nextstep.payments.view.CardListTopBar
import nextstep.payments.view.PaymentCard

@Composable
fun CardListScreen(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CardListViewModel = viewModel()
) {
    val cards by viewModel.cards.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CardListTopBar(
                onAddClick = onAddClick
            )
        },
        modifier = modifier
    ) { innerPadding ->
        CardList(
            cards = cards,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun CardList(
    cards: List<Card>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        items(cards) { card ->
            PaymentCard(
                card = card
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardListPreview(
) {
    CardList(
        cards = listOf(
            Card.mock,
            Card.mock,
            Card.mock,
            Card.mock,
            Card.mock,
            Card.mock,
        )
    )
}