package nextstep.payments.ui

import nextstep.payments.CreditCard

sealed interface CreditCardUiState {
    data object Empty : CreditCardUiState
    data class One(val card: CreditCard) : CreditCardUiState
    data class Many(val cards: List<CreditCard>): CreditCardUiState
}