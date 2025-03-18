package nextstep.payments.ui.creditcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.ui.creditcard.model.CreditCardUiState
import nextstep.payments.data.repository.PaymentCardsRepository

class CreditCardViewModel(private val repository: PaymentCardsRepository = PaymentCardsRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<CreditCardUiState>(CreditCardUiState.Loading)
    val uiState: StateFlow<CreditCardUiState> = _uiState.asStateFlow()

    fun getCards() {
        val cards = repository.getCardList()
        when (cards.size) {
            0 -> _uiState.value = CreditCardUiState.Empty
            1 -> _uiState.value = CreditCardUiState.One(cards.first())
            else -> _uiState.value = CreditCardUiState.Many(cards)
        }
    }

    init {
        getCards()
    }
}
