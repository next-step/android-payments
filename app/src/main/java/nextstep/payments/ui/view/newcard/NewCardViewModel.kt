package nextstep.payments.ui.view.newcard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
}
