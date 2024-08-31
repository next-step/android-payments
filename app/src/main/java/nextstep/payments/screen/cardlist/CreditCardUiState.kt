package nextstep.payments.screen.cardlist

import nextstep.payments.screen.model.CreditCardUiModel

sealed interface CreditCardUiState {
    data object Empty : CreditCardUiState
    data class One(val card: CreditCardUiModel) : CreditCardUiState
    data class Many(val cards: List<CreditCardUiModel>): CreditCardUiState
}