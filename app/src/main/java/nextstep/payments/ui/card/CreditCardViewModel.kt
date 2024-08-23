package nextstep.payments.ui.card

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.model.Card
import nextstep.payments.repository.PaymentCardsRepository


class CreditCardViewModel : ViewModel() {

    private val _cards = MutableStateFlow(PaymentCardsRepository.cards)
    val cards: StateFlow<List<Card>> = _cards.asStateFlow()

    private val _creditCardUiState = MutableStateFlow<CreditCardUiState>(CreditCardUiState.Empty)
    val creditCardUiState: StateFlow<CreditCardUiState> = _creditCardUiState.asStateFlow()

    init {
        when (cards.value.size) {
            0 -> _creditCardUiState.value = CreditCardUiState.Empty
            1 -> _creditCardUiState.value = CreditCardUiState.One(cards.value.first())
            in 2..Int.MAX_VALUE -> _creditCardUiState.value = CreditCardUiState.Many(cards.value)
        }
    }
}
