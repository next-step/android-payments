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
}
