package nextstep.payments.domain

interface PaymentCardsRepository {
    fun getCards(): List<Card>

    fun addCard(
        numbers: String,
        expiredDate: String,
        ownerName: String,
        password: String,
        cardCompany: CardCompany,
    )

    fun updateCard(card: Card)

    fun findCardById(cardId: Int): Card?
}
