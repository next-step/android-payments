package nextstep.payments.ui.cardlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.PaymentCardsRepository

class CardListViewModel(private val repository: PaymentCardsRepository = PaymentCardsRepository) :
    ViewModel() {
    private val _cardListUiState: MutableStateFlow<CardListUiState> =
        MutableStateFlow(CardListUiState.Empty)
    val cardListUiState: StateFlow<CardListUiState> = _cardListUiState.asStateFlow()

    fun fetchCards() {
        val cards = repository.cards

        val uiState = when (cards.size) {
            EMPTY -> CardListUiState.Empty
            ONE_ITEM -> CardListUiState.One(card = cards.first())
            else -> CardListUiState.Many(cards = cards)
        }
        _cardListUiState.update { uiState }
    }

    companion object {
        private const val EMPTY = 0
        private const val ONE_ITEM = 1

    }

}