package nextstep.payments.screens.card.list

import nextstep.payments.domain.Card

sealed interface CardListUiState {
    data object Empty : CardListUiState
    data class One(val card: Card) : CardListUiState
    data class Many(val cards: List<Card>): CardListUiState
}
