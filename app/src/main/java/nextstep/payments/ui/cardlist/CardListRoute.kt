package nextstep.payments.ui.cardlist

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CardListRoute(
    onAddCardClick: () -> Unit,
    cardListViewModel: CardListViewModel = viewModel(),
) {
    CardListScreen(
        cards = listOf(),
        onAddCardClick = onAddCardClick,
    )
}
