package nextstep.payments.ui.screen.creditcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.screen.creditcard.model.toUiModel

class CreditCardViewModel : ViewModel() {

    private val _paymentCardsUiState = MutableStateFlow(CreditCardUiState())
    val paymentCardsUiState = _paymentCardsUiState.asStateFlow()

    init {
        fetchCards()
    }

    fun fetchCards() {
        val cards = PaymentCardsRepository.cards.map { it.toUiModel() }
        _paymentCardsUiState.value = _paymentCardsUiState.value.copy(cards = cards)
    }
}
