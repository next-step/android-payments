package nextstep.payments.ui.card.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.data.RegisteredCreditCards

class CardListViewModel : ViewModel() {

    private val _registeredCreditCards = MutableStateFlow(RegisteredCreditCards(emptyList()))
    val registeredCreditCards: StateFlow<RegisteredCreditCards> =
        _registeredCreditCards.asStateFlow()

    fun fetchCards() {
        _registeredCreditCards.value = RegisteredCreditCards(PaymentCardsRepository.cards)
    }
}
