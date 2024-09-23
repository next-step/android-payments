package nextstep.payments.ui.cardlist

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.CardState
import nextstep.payments.data.CardState.EmptyCard
import nextstep.payments.data.PaymentCardsRepository

class CardListViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository,
) : ViewModel() {
    private val _cards: MutableStateFlow<List<CardState>> = MutableStateFlow(listOf(EmptyCard))
    val cards: StateFlow<List<CardState>> = _cards.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        _cards.value = if (cards.value.size >= 4) repository.cards else repository.cards + EmptyCard
    }
}
