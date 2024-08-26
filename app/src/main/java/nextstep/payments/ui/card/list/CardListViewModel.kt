package nextstep.payments.ui.card.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.RegisteredCreditCards
import nextstep.payments.data.PaymentCardsRepository

class CardListViewModel : ViewModel() {

    private val _Registered_creditCards = MutableStateFlow(RegisteredCreditCards(emptyList()))
    val registeredCreditCards: StateFlow<RegisteredCreditCards> = _Registered_creditCards.asStateFlow()

    init {
        fetchCards()
    }

    fun fetchCards() {
        _Registered_creditCards.value = RegisteredCreditCards(PaymentCardsRepository.cards)
    }
}
