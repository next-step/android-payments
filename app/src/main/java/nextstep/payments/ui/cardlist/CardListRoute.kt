package nextstep.payments.ui.cardlist

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

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
        cards = cards,
        onAddCardClick = onAddCardClick,
    )
}
