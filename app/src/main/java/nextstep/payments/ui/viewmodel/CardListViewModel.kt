package nextstep.payments.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository

class CardListViewModel(
    private val paymentRepsoitory: PaymentCardsRepository = PaymentCardsRepository,
): ViewModel() {
    private var _cards = MutableStateFlow<List<Card>>(emptyList())
    val cards: StateFlow<List<Card>> get() = _cards

    fun getCards() {
        _cards.value = paymentRepsoitory.cards
    }
}