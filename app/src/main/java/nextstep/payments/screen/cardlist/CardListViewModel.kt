package nextstep.payments.screen.cardlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.screen.model.toUiModel

class CardListViewModel : ViewModel() {

    private val _cardListUiState= MutableStateFlow<CreditCardUiState>(CreditCardUiState.Empty)
    val cardListUiState : StateFlow<CreditCardUiState> = _cardListUiState.asStateFlow()

    fun fetchCardList() {
        PaymentCardsRepository.creditCards.let { creditCards ->
            _cardListUiState.value =
                if(creditCards.isEmpty()) CreditCardUiState.Empty
                else if(creditCards.size == 1) CreditCardUiState.One(creditCards.first().toUiModel())
                else CreditCardUiState.Many(creditCards.map { it.toUiModel() })
        }

    }
}