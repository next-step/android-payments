package nextstep.payments.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.repository.PaymentCardsRepository

class CardListViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _cardsUiState = MutableStateFlow<CardsUiState>(CardsUiState.Empty)
    val cardsUiState: StateFlow<CardsUiState> = _cardsUiState.asStateFlow()

    fun fetchCards() {
        val cards = repository.cards

        _cardsUiState.value =  when (cards.size) {
            0 -> CardsUiState.Empty
            1 -> CardsUiState.One(cards.first())
            else -> CardsUiState.Many(cards)
        }
    }
}