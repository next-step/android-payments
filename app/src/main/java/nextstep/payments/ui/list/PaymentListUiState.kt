package nextstep.payments.ui.list

import nextstep.payments.ui.domain.model.Card

sealed interface PaymentListUiState {
    data object Empty : PaymentListUiState
    data class One(val card: Card) : PaymentListUiState
    data class Many(val cards: List<Card>) : PaymentListUiState
}
