package nextstep.payments.cart_list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.repository.PaymentCardsRepository

class CardListViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {
    private val _cardListUiState = MutableStateFlow<CardListUiState>(CardListUiState.Empty)
    val cardListUiState: StateFlow<CardListUiState> = _cardListUiState.asStateFlow()

    fun fetchCards() {
        if (repository.cards.isEmpty()) {
            _cardListUiState.value = CardListUiState.Empty
        } else if (repository.cards.size == 1) {
            _cardListUiState.value = CardListUiState.One(repository.cards.first())
        } else {
            _cardListUiState.value = CardListUiState.Many(repository.cards)
        }
    }
}