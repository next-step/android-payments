package nextstep.payments.creditcard.model

import nextstep.payments.model.CreditCard

sealed interface CreditCardUiState {
    data object Loading : CreditCardUiState
    data object Empty : CreditCardUiState
    data class One(val card: CreditCard) : CreditCardUiState
    data class Many(val cards: List<CreditCard>) : CreditCardUiState
}
