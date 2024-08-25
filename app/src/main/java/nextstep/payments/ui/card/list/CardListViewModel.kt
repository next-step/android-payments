package nextstep.payments.ui.card.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardRepository

class CardListViewModel(
    private val repository: PaymentCardRepository = PaymentCardRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<CardListUiState>(CardListUiState.Empty)
    val uiState: StateFlow<CardListUiState> = _uiState.asStateFlow()

    fun fetchCards() {
        val cards = repository.cards
        _uiState.value = when {
            cards.size == 1 -> CardListUiState.One(cards.first())
            cards.size > 1 -> CardListUiState.Many(cards)
            else -> CardListUiState.Empty
        }
    }

}
