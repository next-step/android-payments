package nextstep.payments.data

object PaymentCardsRepository {

    private val _cards = mutableListOf<CardData>()
    val cards: List<CardData> get() = _cards.toList()

    fun addCard(card: CardData) {
        _cards.add(card)
    }

    fun getCard(cardId: String): CardData? {
        return _cards.find { it.id == cardId }
    }

    fun updateCard(card: CardData) {
        val index = _cards.indexOfFirst { it.id == card.id }
        if (index != -1) {
            _cards[index] = card
        }
    }

    fun remove(cardId: String) {
        _cards.removeIf { it.id == cardId }
    }
}
