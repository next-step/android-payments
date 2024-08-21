package nextstep.payments.ui.creditcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository

class CreditCardViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<CreditCardUiState>(CreditCardUiState.Empty)
    val uiState = _uiState.asStateFlow()

    init {
        val cards = PaymentCardsRepository.cards
        when (cards.size) {
            0 -> _uiState.value = CreditCardUiState.Empty
            1 -> _uiState.value = CreditCardUiState.One(cards.first())
            else -> _uiState.value = CreditCardUiState.Many(cards)
        }
    }
}
