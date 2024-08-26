package nextstep.payments.ui.screen.creditcard

import nextstep.payments.ui.screen.creditcard.model.CardModel

data class CreditCardUiState(
    val cards: List<CardModel> = emptyList()
) {
    fun isEmptyCard() = cards.isEmpty()
    fun isOneCard() = cards.size == 1
    fun isManyCard() = cards.size > 1
}
