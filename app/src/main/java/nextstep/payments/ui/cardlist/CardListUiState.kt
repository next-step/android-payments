package nextstep.payments.ui.cardlist

import nextstep.payments.model.card.CreditCard

internal sealed interface CardListUiState {
    data object Empty : CardListUiState
    data class One(val card: CreditCard) : CardListUiState
    data class Many(val cards: List<CreditCard>) : CardListUiState

    companion object {
        fun from(cards: List<CreditCard>): CardListUiState = when {
            cards.isEmpty() -> Empty
            cards.size == 1 -> One(cards.first())
            else -> Many(cards)
        }
    }
}
