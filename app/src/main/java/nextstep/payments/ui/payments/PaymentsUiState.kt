package nextstep.payments.ui.payments

import nextstep.payments.model.CreditCard

sealed interface PaymentsUiState {
    val isTopBarAddEnabled: Boolean

    data object Empty : PaymentsUiState {
        override val isTopBarAddEnabled = false
    }

    data class One(val card: CreditCard) : PaymentsUiState {
        override val isTopBarAddEnabled = false
    }

    data class Many(val cards: List<CreditCard>) : PaymentsUiState {
        override val isTopBarAddEnabled = true
    }
}