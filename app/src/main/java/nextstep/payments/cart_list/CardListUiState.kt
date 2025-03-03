package nextstep.payments.cart_list

import nextstep.payments.data.Card

sealed interface CardListUiState {
    data object Empty : CardListUiState
    data class One(val card: Card) : CardListUiState
    data class Many(val cards: List<Card>) : CardListUiState
}