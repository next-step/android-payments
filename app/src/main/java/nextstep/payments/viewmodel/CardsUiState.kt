package nextstep.payments.viewmodel

import nextstep.payments.data.model.Card

sealed interface CardsUiState {
    data object Empty : CardsUiState
    data class One(val card: Card) : CardsUiState
    data class Many(val cards: List<Card>) : CardsUiState
}
