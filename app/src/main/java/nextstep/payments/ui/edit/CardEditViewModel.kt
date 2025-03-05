package nextstep.payments.ui.edit

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.CardEditActivity
import nextstep.payments.data.model.BankType
import nextstep.payments.data.model.Card
import nextstep.payments.data.repository.PaymentCardsRepository

class CardEditViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val repository: PaymentCardsRepository,
) : ViewModel() {
    private val _card = MutableStateFlow(Card())
    val card: StateFlow<Card> = _card.asStateFlow()

    private val originCard
        get() = savedStateHandle.get<Int>(CardEditActivity.EXTRA_CARD_ID)
            ?.let(repository::getCardById) ?: throw NullPointerException()

    init {
        _card.update { originCard }
    }

    private val _cardUpdated = MutableStateFlow(false)
    val cardUpdated: StateFlow<Boolean> = _cardUpdated.asStateFlow()

    private val _cardUpdateFailed = Channel<Unit>()
    val cardUpdateFailed: Flow<Unit> = _cardUpdateFailed.consumeAsFlow()

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

    fun updateCard() {
        if (originCard == _card.value) {
            _cardUpdateFailed.trySend(Unit)
            return
        }
        
        repository.update(_card.value)
        _cardUpdated.update { true }
    }

    companion object {
        fun getFactory(repository: PaymentCardsRepository): ViewModelProvider.Factory =
            object : AbstractSavedStateViewModelFactory() {
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return CardEditViewModel(handle, repository) as T
                }
            }

    }
}
