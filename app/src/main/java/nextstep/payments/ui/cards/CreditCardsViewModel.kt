package nextstep.payments.ui.cards

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository

class CreditCardsViewModel : ViewModel(){

    private val _creditCardState = MutableStateFlow<CreditCardUiState>(CreditCardUiState.Empty)
    val creditCardState: StateFlow<CreditCardUiState> = _creditCardState.asStateFlow()

    init {
        fetchCards()
    }

    fun fetchCards() {
        val cardCount = PaymentCardsRepository.cards.count()
        _creditCardState.value = when (cardCount) {
            0 -> CreditCardUiState.Empty
            1 -> CreditCardUiState.One(PaymentCardsRepository.cards.first())
            else -> CreditCardUiState.Many(PaymentCardsRepository.cards)
        }
    }
}