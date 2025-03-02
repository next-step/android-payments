package nextstep.payments.feature.cardlist

import nextstep.payments.model.Card

sealed interface CardUiState {
    data object Empty : CardUiState
    data class One(val card: Card) : CardUiState
    data class Many(val cards: List<Card>): CardUiState
}