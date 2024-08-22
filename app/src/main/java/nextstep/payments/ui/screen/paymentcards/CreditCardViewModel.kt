package nextstep.payments.ui.screen.paymentcards

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.screen.paymentcards.model.toCreditCard

class CreditCardViewModel : ViewModel() {

    private val _paymentCardsUiState = MutableStateFlow(CreditCardUiState())
    val paymentCardsUiState = _paymentCardsUiState.asStateFlow()

    init {
        val cards = PaymentCardsRepository.cards.map { it.toCreditCard() }
        _paymentCardsUiState.value = _paymentCardsUiState.value.copy(cards = cards)
    }
}
