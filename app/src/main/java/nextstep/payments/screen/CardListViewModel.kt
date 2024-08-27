package nextstep.payments.screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.data.model.CreditCard

class CardListViewModel : ViewModel() {

    private val _creditCardList = MutableStateFlow<List<CreditCard>>(emptyList())
    val creditCardList : StateFlow<List<CreditCard>> = _creditCardList.asStateFlow()

    fun fetchCardList() {
        _creditCardList.value = PaymentCardsRepository.creditCards
    }
}