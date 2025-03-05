package nextstep.payments.domain

interface PaymentCardsRepository {
    fun getCards(): List<Card>

    fun addCard(card: Card)

    fun updateCard(oldCard: Card, newCard: Card)
}
