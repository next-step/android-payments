package nextstep.payments.card

interface CardRepository {
    fun addCard(card: Card)

    fun getAllCards(): List<Card>
}

object DefaultCardRepository : CardRepository {
    private val cards: MutableList<Card> = mutableListOf()

    override fun addCard(card: Card) {
        cards.add(card)
    }

    override fun getAllCards(): List<Card> {
        return cards.toList()
    }
}
