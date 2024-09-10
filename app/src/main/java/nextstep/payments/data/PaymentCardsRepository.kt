package nextstep.payments.data

object PaymentCardsRepository {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards

    fun addCard(card: Card) {
        if (card.id == 0) card.id = createId()
        _cards.add(card)
    }

    fun removeAllCard() {
        _cards.clear()
    }

    fun editCard(newCard: Card) {
        val index = _cards.indexOfFirst { it.id == newCard.id }
        _cards[index] = newCard
    }

    private fun createId(): Int {
        return _cards.maxOfOrNull { it.id }?.plus(1) ?: 1
    }
}
