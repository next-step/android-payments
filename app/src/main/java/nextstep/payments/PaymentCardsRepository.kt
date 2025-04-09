package nextstep.payments

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.model.Card

object PaymentCardsRepository {
    private val _cards = MutableStateFlow<List<Card>>(emptyList())
    val cards = _cards.asStateFlow()

    fun addCard(card: Card) {
        _cards.update {
            it + card
        }
    }
}