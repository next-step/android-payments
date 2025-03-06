package nextstep.payments.screens.card.list

import nextstep.payments.screens.card.state.CardState

sealed interface CardListUiState {
    data object Empty : CardListUiState
    data class One(val card: CardState) : CardListUiState
    data class Many(val cards: List<CardState>) : CardListUiState
}
