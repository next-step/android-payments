package nextstep.payments.screens.card.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.InMemoryPaymentCardsRepository
import nextstep.payments.domain.Card
import nextstep.payments.domain.PaymentCardsRepository
import nextstep.payments.screens.card.mapper.toState

class CardListViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = InMemoryPaymentCardsRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<CardListUiState> =
        MutableStateFlow(CardListUiState.Empty)
    val uiState: StateFlow<CardListUiState> = _uiState.asStateFlow()

    init {
        fetchCards()
    }

    fun fetchCards() {
        val cards = paymentCardsRepository.getCards()

        if (cards.isEmpty()) {
            _uiState.update { CardListUiState.Empty }
            return
        }

        if (cards.size == 1) {
            _uiState.update { CardListUiState.One(cards.first().toState()) }
            return
        }

        _uiState.update { CardListUiState.Many(cards.map(Card::toState)) }
    }
}
