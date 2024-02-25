package nextstep.payments.ui.data.repository

import nextstep.payments.ui.domain.model.Card
import nextstep.payments.ui.domain.model.CardRegistrationForm
import nextstep.payments.ui.domain.repository.CardRepository

internal class CardRepositoryImpl : CardRepository {

    override suspend fun getCardList(): List<Card> {
        return cachedCards
    }

    override suspend fun addCard(form: CardRegistrationForm) {
        val id = nextId().toString()
        val card = Card(
            id = id,
            cardNumber = form.cardNumber,
            ownerName = form.ownerName,
            expiredDate = form.expiredDate,
            imageUrl = "https://picsum.photos/id/${id}/208/124"
        )
        cachedCards = cachedCards + card
    }

    companion object {
        private var cachedCards: List<Card> = emptyList()
        private var cardId: Int = 0

        private fun nextId(): Int {
            return cardId++
        }
    }
}
