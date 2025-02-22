package nextstep.payments.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.CardUiState

class CardListViewModel(
    private val paymentRepsoitory: PaymentCardsRepository = PaymentCardsRepository,
): ViewModel() {
    private var _cards = MutableStateFlow<CardUiState<List<Card>>>(CardUiState.Empty)
    val cards = _cards.asStateFlow()

    fun getCards() {
        val result = paymentRepsoitory.cards
        _cards.value = when {
            result.isEmpty() -> CardUiState.Empty
            result.size == 1 -> CardUiState.One(result.first()) // ✅ UiState<Card>
            else -> CardUiState.Many(result) // ✅ UiState<List<Card>>
        }
    }
}