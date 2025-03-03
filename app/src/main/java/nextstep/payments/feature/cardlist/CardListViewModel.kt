package nextstep.payments.feature.cardlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.repository.PaymentCardsRepository

class CardListViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _cardsUiState = MutableStateFlow<CardUiState>(CardUiState.Empty)
    val cardsUiState: StateFlow<CardUiState> = _cardsUiState.asStateFlow()

    fun fetchCards() {
        when {
            repository.cards.isEmpty() -> {
                _cardsUiState.value = CardUiState.Empty
            }

            repository.cards.size == 1 -> {
                _cardsUiState.value = CardUiState.One(repository.cards.first())
            }

            else -> {
                _cardsUiState.value = CardUiState.Many(repository.cards)
            }
        }
    }

}
