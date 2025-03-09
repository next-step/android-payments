package nextstep.payments.screens.card.list

import nextstep.payments.screens.card.uistate.CardUiState

sealed interface CardListUiState {
    data object Empty : CardListUiState
    data class One(val cardUiState: CardUiState) : CardListUiState
    data class Many(val cardUiStates: List<CardUiState>) : CardListUiState
}
