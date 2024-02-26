package nextstep.payments.ui.payments

import nextstep.payments.domain.PaymentCard

sealed interface PaymentCardsUiState {
    data object Empty : PaymentCardsUiState
    data class One(val card: PaymentCard) : PaymentCardsUiState
    data class Many(val cards: List<PaymentCard>) : PaymentCardsUiState
}
