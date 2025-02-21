package nextstep.payments.viewmodel

import nextstep.payments.data.model.CardModel

sealed interface CardsUiState {
    data object Empty : CardsUiState
    data class One(val card: CardModel) : CardsUiState
    data class Many(val cards: List<CardModel>) : CardsUiState
}
