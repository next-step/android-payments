package nextstep.payments.cardlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.model.CreditCard
import nextstep.payments.repository.PaymentCardsRepository

internal class CardListViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(determineUiState(paymentCardsRepository.cards))
    val uiState = _uiState.asStateFlow()

    fun fetchCards() {
        val cards = paymentCardsRepository.cards
        _uiState.update { determineUiState(cards) }
    }

    private fun determineUiState(cards: List<CreditCard>): CardListUiState = when {
        cards.isEmpty() -> CardListUiState.Empty
        cards.size == 1 -> CardListUiState.One(cards.first())
        else -> CardListUiState.Many(cards)
    }
}