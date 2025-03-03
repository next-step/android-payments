package nextstep.payments.cart_list

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.Card
import nextstep.payments.repository.PaymentCardsRepository

class CardListViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) {
    fun fetchCards(): List<Card> {
        return repository.cards
    }
}