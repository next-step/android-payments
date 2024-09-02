package nextstep.payments.data

object PaymentCardsRepository {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun removeAllCard() {
        _cards.clear()
    }

    fun editCard(oldCard: Card?, newCard: Card) {
        val index = _cards.indexOfFirst { it.id == oldCard!!.id }
        _cards[index] = newCard
    }

    fun createId(): Int {
        return _cards.maxOfOrNull { it.id }?.plus(1) ?: 1
    }
}
