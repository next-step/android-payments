package nextstep.payments.list

import nextstep.payments.model.CreditCard

sealed interface CardListState {
    data object Empty : CardListState
    data class Single(val card: CreditCard) : CardListState
    data class Multiple(val cards: List<CreditCard>): CardListState
}
