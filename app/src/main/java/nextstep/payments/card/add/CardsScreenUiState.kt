package nextstep.payments.card.add

import nextstep.payments.card.Card

sealed interface CardsScreenUiState {
    data object Empty : CardsScreenUiState

    data class SingleCard(val card: Card) : CardsScreenUiState

    data class MultipleCards(val cards: List<Card>) : CardsScreenUiState
}
