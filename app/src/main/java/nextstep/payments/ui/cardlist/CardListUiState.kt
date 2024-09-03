package nextstep.payments.ui.cardlist

import nextstep.payments.model.CreditCard

internal sealed interface CardListUiState {
    data object Empty : CardListUiState
    data class One(val card: CreditCard) : CardListUiState
    data class Many(val cards: List<CreditCard>) : CardListUiState
}

internal fun CardListUiState(cards: List<CreditCard>): CardListUiState = when {
    cards.isEmpty() -> CardListUiState.Empty
    cards.size == 1 -> CardListUiState.One(cards.first())
    else -> CardListUiState.Many(cards)
}