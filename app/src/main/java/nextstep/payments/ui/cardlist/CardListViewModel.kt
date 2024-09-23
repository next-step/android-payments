package nextstep.payments.ui.cardlist

import androidx.lifecycle.ViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.CardState
import nextstep.payments.data.CardState.EmptyCard
import nextstep.payments.data.PaymentCardsRepository

class CardListViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository,
) : ViewModel() {
    private val _cards: MutableStateFlow<ImmutableList<CardState>> =
        MutableStateFlow(persistentListOf(EmptyCard))
    val cards: StateFlow<ImmutableList<CardState>> = _cards.asStateFlow()

    init {
        refresh()
    }

    fun refresh() {
        _cards.value = when (cards.value.size >= 4) {
            true -> repository.cards
            false -> (repository.cards + EmptyCard).toImmutableList()
        }
    }
}
