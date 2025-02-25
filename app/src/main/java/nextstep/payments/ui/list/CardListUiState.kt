package nextstep.payments.ui.list

import nextstep.payments.data.model.Card

sealed interface CardListUiState {
    data object Empty : CardListUiState
    data class One(val card: Card) : CardListUiState
    data class Many(val cards: List<Card>) : CardListUiState

    companion object {
        fun of(cards: List<Card>): CardListUiState {
            return when {
                cards.isEmpty() -> Empty
                cards.size == 1 -> One(cards.first())
                else -> Many(cards)
            }
        }
    }
}
