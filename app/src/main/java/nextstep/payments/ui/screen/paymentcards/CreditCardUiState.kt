package nextstep.payments.ui.screen.paymentcards

import nextstep.payments.ui.screen.paymentcards.model.CreditCard

data class CreditCardUiState(
    val cards: List<CreditCard> = emptyList()
) {
    fun isEmptyCard() = cards.isEmpty()
    fun isOneCard() = cards.size == 1
    fun isManyCard() = cards.size > 1
}
