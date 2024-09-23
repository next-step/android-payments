package nextstep.payments.data

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import nextstep.payments.data.CardState.Card

object PaymentCardsRepository {

    private val _cards = mutableListOf<Card>()
    val cards: ImmutableList<Card> get() = _cards.toImmutableList()

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
