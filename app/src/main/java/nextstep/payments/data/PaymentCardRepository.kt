package nextstep.payments.data

import android.util.Log
import nextstep.payments.model.Card

object PaymentCardRepository {

    private var idIndex: Int = 1

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        val index = cards.indexOfFirst { it.id == card.id }
        if (index >= 0) {
            _cards[index] = card.copy(id = cards[index].id)
        } else {
            _cards.add(card.copy(id = idIndex++))
        }
    }
}
