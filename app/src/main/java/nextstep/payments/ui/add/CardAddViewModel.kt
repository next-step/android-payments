package nextstep.payments.ui.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.model.BankType
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository

class CardAddViewModel(
    private val repository: PaymentCardsRepository,
) : ViewModel() {

    private val _card = MutableStateFlow(Card())
    val card: StateFlow<Card> = _card.asStateFlow()

    private val _cardAdded = MutableStateFlow(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _cardAddFailed = MutableStateFlow(false)
    val cardAddFailed: StateFlow<Boolean> = _cardAddFailed.asStateFlow()

    private val _bankSelectSheetOpened = MutableStateFlow(true)
    val bankSelectSheetOpened: StateFlow<Boolean> = _bankSelectSheetOpened.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _card.update {
            it.copy(number = cardNumber.take(16))
        }
    }

    fun setExpiredDate(expiredDate: String) {
        _card.update {
            it.copy(expiredDate = expiredDate.take(4))
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

    fun setBankType(bankType: BankType) {
        _card.update {
            it.copy(bankType = bankType)
        }
    }

    fun addCard() {
        if (_card.value.bankType == BankType.NOT_SELECTED) {
            _cardAddFailed.update { true }
            return
        }

        repository.addCard(_card.value)
        _cardAdded.update { true }
    }

    fun resetCardAddFailed() {
        _cardAddFailed.update { false }
    }

    fun setBankSelectSheetOpened(isOpened: Boolean) {
        _bankSelectSheetOpened.update { isOpened }
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
