package nextstep.payments.data

import nextstep.payments.model.Card

object PaymentCardsRepository {

    private val _cards = LinkedHashMap<Int, Card>()
    val cards: List<Card> get() = _cards.values.toList()

    fun upsertCard(card: Card): Boolean =
        _cards.put("${card.type.bankName}${card.number}".hashCode(), card) != card

    fun clear() {
        _cards.clear()
    }
}