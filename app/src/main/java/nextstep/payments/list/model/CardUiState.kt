package nextstep.payments.list.model

import nextstep.payments.common.model.Card

sealed interface CardUiState {
    data object Empty : CardUiState
    data class One(val card: Card) : CardUiState
    data class Many(val cards: List<Card>): CardUiState
}
