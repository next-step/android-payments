package nextstep.payments.data

object PaymentCardsRepository {

    private val _cards = mutableListOf<Card>()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }

    fun modifyCard(card: Card) : Boolean {
        return _cards.indexOfFirst { it.id == card.id }
            .takeIf { it != -1 }?.let { index ->
                val oldCard = _cards[index]
                if (hasChanged(oldCard, card)) {
                    _cards[index] = card
                    true
                } else {
                    false
                }
            } ?: false
    }

    private fun hasChanged(oldCard: Card, newCard: Card): Boolean {
        return oldCard.cardNumber != newCard.cardNumber ||
                oldCard.expiredDate != newCard.expiredDate ||
                oldCard.ownerName != newCard.ownerName ||
                oldCard.password != newCard.password
    }
}