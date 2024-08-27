package nextstep.payments.ui.cards

import nextstep.payments.model.Card

sealed interface CreditCardUiState {
    data object Empty : CreditCardUiState
    data class One(val card: Card) : CreditCardUiState
    data class Many(val cards: List<Card>): CreditCardUiState
}