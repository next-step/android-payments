package nextstep.payments.ui.payments

import nextstep.payments.model.CreditCard

sealed interface PaymentsUiState {
    data object Empty : PaymentsUiState
    data class One(val card: CreditCard) : PaymentsUiState
    data class Many(val cards: List<CreditCard>) : PaymentsUiState
}
