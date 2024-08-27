package nextstep.payments.ui.state

import nextstep.payments.ui.model.PaymentCardModel

sealed interface PaymentCardUiState {
    data object Empty : PaymentCardUiState
    data class One(val card: PaymentCardModel) : PaymentCardUiState
    data class Many(val cards: List<PaymentCardModel>): PaymentCardUiState
}
