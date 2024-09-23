package nextstep.payments.ui.cardlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.Card
import nextstep.payments.data.PaymentCardsRepository

class CardListViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository,
) : ViewModel() {
    private val _cards: MutableStateFlow<List<Card>> = MutableStateFlow(listOf())
    val cards: StateFlow<List<Card>> = _cards.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        _cards.value = repository.cards
    }
}
