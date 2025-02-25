package nextstep.payments

import androidx.lifecycle.ViewModel
import nextstep.payments.model.Card

class CardListViewModel(
    private val cardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()
}
