package nextstep.payments.ui.creditcard

import nextstep.payments.model.Card

sealed interface CreditCardEvent {
    data object OnNewCardClick : CreditCardEvent

    data class OnCardClick(
        val card: Card,
    ) : CreditCardEvent
}
