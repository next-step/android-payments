package nextstep.payments.data

object PaymentCardsRepository {

    private val _cards = mutableListOf<CardData>()
    val cards: List<CardData> get() = _cards.toList()

    fun addCard(card: CardData) {
        _cards.add(card)
    }
}
