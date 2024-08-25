package nextstep.payments.ui.card.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.CreditCard
import nextstep.payments.data.PaymentCardsRepository

class CardListViewModel : ViewModel() {

    private val _creditCard = MutableStateFlow(CreditCard(emptyList()))
    val creditCard: StateFlow<CreditCard> = _creditCard.asStateFlow()

    init {
        fetchCards()
    }

    fun fetchCards() {
        _creditCard.value = CreditCard(PaymentCardsRepository.cards)
    }
}
