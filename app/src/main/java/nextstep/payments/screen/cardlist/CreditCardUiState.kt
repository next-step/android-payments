package nextstep.payments.screen.cardlist

import nextstep.payments.data.model.CreditCard

sealed interface CreditCardUiState {
    data object Empty : CreditCardUiState
    data class One(val card: CreditCard) : CreditCardUiState
    data class Many(val cards: List<CreditCard>): CreditCardUiState
}