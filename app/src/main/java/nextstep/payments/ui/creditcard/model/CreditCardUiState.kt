package nextstep.payments.ui.creditcard.model

import nextstep.payments.ui.model.CreditCardType

sealed interface CreditCardUiState {
    data object Loading : CreditCardUiState
    data object Empty : CreditCardUiState
    data class One(val card: CreditCardType.RegisteredCard) : CreditCardUiState
    data class Many(val cards: List<CreditCardType.RegisteredCard>) : CreditCardUiState
}
