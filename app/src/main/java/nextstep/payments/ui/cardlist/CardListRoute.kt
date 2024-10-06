package nextstep.payments.ui.cardlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.data.CardState.EmptyCard

@Composable
fun CardListRoute(
    onAddCardClick: () -> Unit,
    cardListViewModel: CardListViewModel = viewModel(),
) {
    val cards by cardListViewModel.cards.collectAsStateWithLifecycle()

    LaunchedEffect(cardListViewModel) {
        cardListViewModel.refresh()
    }

    CardListScreen(
        isEnabledOfAddButton = !cards.contains(EmptyCard),
        isEmptyOfRegisteredCards = cards.first() is EmptyCard,
        cards = cards,
        onAddCardClick = onAddCardClick,
    )
}
