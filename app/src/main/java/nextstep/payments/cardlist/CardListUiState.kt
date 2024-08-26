package nextstep.payments.cardlist

import nextstep.payments.model.CreditCard

internal sealed interface CardListUiState {
    data object Empty : CardListUiState
    data class One(val card: CreditCard) : CardListUiState
    data class Many(val cards: List<CreditCard>) : CardListUiState
}