package nextstep.payments.ui.card.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.CreditCard
import nextstep.payments.data.PaymentCardsRepository

class CardListViewModel : ViewModel() {

    private val _Credit_card = MutableStateFlow(CreditCard(emptyList()))
    val creditCard: StateFlow<CreditCard> = _Credit_card.asStateFlow()

    init {
        fetchCards()
    }

    fun fetchCards() {
        _Credit_card.value = CreditCard(PaymentCardsRepository.cards)
    }
}
