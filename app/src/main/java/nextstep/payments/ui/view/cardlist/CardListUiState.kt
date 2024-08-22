package nextstep.payments.ui.view.cardlist

import nextstep.payments.model.PaymentCardModel

sealed interface CardListUiState {
    data object Empty : CardListUiState
    data class One(val card: PaymentCardModel) : CardListUiState
    data class Many(val cards: List<PaymentCardModel>) : CardListUiState
}
