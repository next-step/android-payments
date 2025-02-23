package nextstep.payments.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository

class CardAddViewModel(
    private val repository: PaymentCardsRepository,
) : ViewModel() {

    private val _card = MutableStateFlow(Card())
    val card: StateFlow<Card> = _card.asStateFlow()

    private val _cardAdded = MutableStateFlow(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _card.update {
            it.copy(number = cardNumber)
        }
    }

    fun setExpiredDate(expiredDate: String) {
        _card.update {
            it.copy(expiredDate = expiredDate)
        }
    }

    fun setOwnerName(ownerName: String) {
        _card.update {
            it.copy(ownerName = ownerName)
        }
    }

    fun setPassword(password: String) {
        _card.update {
            it.copy(password = password)
        }
    }

    fun addCard() {
        repository.addCard(_card.value)
        _cardAdded.value = true
    }

    companion object {
        fun getFactory(repository: PaymentCardsRepository) =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return CardAddViewModel(repository) as T
                }
            }

    }
}
