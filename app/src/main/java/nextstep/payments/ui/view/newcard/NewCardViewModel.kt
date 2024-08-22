package nextstep.payments.ui.view.newcard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardRepository
import nextstep.payments.model.PaymentCardModel

class NewCardViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val paymentCard = savedStateHandle.get<PaymentCardModel>(NewCardActivity.EXTRA_PAYMENT_CARD)

    private val _cardNumber = MutableStateFlow(paymentCard?.cardNumber.orEmpty())
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow(paymentCard?.expiredDate.orEmpty())
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow(paymentCard?.ownerName.orEmpty())
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow(paymentCard?.password.orEmpty())
    val password: StateFlow<String> = _password.asStateFlow()

    private val _finishEvent = MutableSharedFlow<Unit>(replay = 0, extraBufferCapacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)
    val finishEvent = _finishEvent.asSharedFlow()

    fun setCardNumber(cardNumber: String) {
        _cardNumber.value = cardNumber
    }

    fun setExpiredDate(expiredDate: String) {
        _expiredDate.value = expiredDate
    }

    fun setOwnerName(ownerName: String) {
        _ownerName.value = ownerName
    }

    fun setPassword(password: String) {
        _password.value = password
    }

    fun saveCard() {
        val cardNumber = _cardNumber.value
        val expiredDate = _expiredDate.value
        val ownerName = _ownerName.value
        val password = _password.value
        if (cardNumber.isNotBlank() &&
            expiredDate.isNotBlank() &&
            password.isNotBlank()
        ) {
            PaymentCardRepository.addCard(
                PaymentCardModel(
                    cardNumber = cardNumber,
                    expiredDate = expiredDate,
                    ownerName = ownerName,
                    password = password
                )
            )
            _finishEvent.tryEmit(Unit)
        }
    }
}
