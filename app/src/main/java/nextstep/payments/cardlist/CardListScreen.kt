package nextstep.payments.cardlist

import androidx.compose.runtime.Composable

@Composable
internal fun CardListScreen(
    uiState: CardListUiState
) {
    when (uiState) {
        is CardListUiState.Empty -> TODO()

        is CardListUiState.One -> {
            CardListOneScreen(uiState.card)
        }

        is CardListUiState.Many -> TODO()
    }
}