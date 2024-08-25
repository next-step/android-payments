package nextstep.payments.ui.card.list

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.CardList
import nextstep.payments.data.PaymentCardsRepository

class CardListViewModel : ViewModel() {

    private val _cardList = MutableStateFlow(CardList(emptyList()))
    val cardList: StateFlow<CardList> = _cardList.asStateFlow()

    init {
        fetchCards()
    }

    fun fetchCards() {
        _cardList.value = CardList(PaymentCardsRepository.cards)
    }
}
