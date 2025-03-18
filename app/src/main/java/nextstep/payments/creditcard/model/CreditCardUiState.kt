package nextstep.payments.creditcard.model

import nextstep.payments.model.CreditCardType

sealed interface CreditCardUiState {
    data object Loading : CreditCardUiState
    data object Empty : CreditCardUiState
    data class One(val card: CreditCardType.CardInfo) : CreditCardUiState
    data class Many(val cards: List<CreditCardType.CardInfo>) : CreditCardUiState
}
