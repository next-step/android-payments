package nextstep.payments.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.payments.component.EmptyPaymentCard
import nextstep.payments.component.PaymentCard
import nextstep.payments.model.CreditCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(modifier: Modifier = Modifier) {


}

@Composable
fun CardListScreen(
    state: CardListState,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                showAddButton = state is CardListState.Multiple,
                onAddClick = onAddClick
            )
        }
    ) { innerPadding ->
        when (state) {
            CardListState.Empty -> EmptyCardContent(
                modifier = modifier.padding(innerPadding),
                onAddClick = onAddClick
            )
            is CardListState.Single -> SingleCardContent(
                modifier = modifier.padding(innerPadding),
                card = state.card,
                onAddClick = onAddClick
            )
            is CardListState.Multiple -> MultipleCardContent(
                modifier = modifier.padding(innerPadding),
                cards = state.cards
            )
        }
    }
}

@Composable
private fun EmptyCardContent(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "새로운 카드를 등록해주세요")

        Spacer(modifier = Modifier.height(30.dp))

        EmptyPaymentCard(modifier = Modifier.clickable(onClick = onAddClick))
    }
}

@Composable
private fun SingleCardContent(
    card: CreditCard,
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PaymentCard(card = card)

        Spacer(modifier = Modifier.height(30.dp))

        EmptyPaymentCard(modifier = Modifier.clickable(onClick = onAddClick))
    }
}

@Composable
private fun MultipleCardContent(
    cards: List<CreditCard>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize().padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(30.dp)
    ) {
        items(cards) {
            PaymentCard(card = it)
        }
    }
}

private class CardListStateProvider : PreviewParameterProvider<CardListState> {
    override val values: Sequence<CardListState>
        get() = sequenceOf(
            CardListState.Empty,
            CardListState.Single(CreditCard.emptyCard),
            CardListState.Multiple(listOf(CreditCard.emptyCard, CreditCard.emptyCard))
        )

}

@Preview
@Composable
private fun CardListScreenPreview(@PreviewParameter(CardListStateProvider::class) state: CardListState) {
    PaymentsTheme {
        CardListScreen(
            state = state,
            onAddClick = {}
        )
    }
}