package nextstep.payments.ui.cardlist

import nextstep.payments.model.Card

sealed interface CardListUiState {
    data object Empty : CardListUiState
    data class One(val card: Card) : CardListUiState
    data class Many(val cards: List<Card>) : CardListUiState
}