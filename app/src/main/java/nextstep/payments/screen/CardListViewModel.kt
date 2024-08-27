package nextstep.payments.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.data.model.Card

class CardListViewModel : ViewModel() {

    private val _cardList = MutableStateFlow<List<Card>>(emptyList())
    val cardList : StateFlow<List<Card>> = _cardList.asStateFlow()

    fun fetchCardList() {
        _cardList.value = PaymentCardsRepository.cards
    }
}