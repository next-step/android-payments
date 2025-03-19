package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class NewCardViewModel : ViewModel() {

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        if (!NewCardInputValidator.validateCardNumber(cardNumber))
            return
        _cardNumber.value = cardNumber
    }

    fun setExpiredDate(expiredDate: String) {
        if (!NewCardInputValidator.validateExpiredDate(expiredDate))
            return
        _expiredDate.value = expiredDate
    }

    fun setOwnerName(ownerName: String) {
        if (!NewCardInputValidator.validateOwnerName(ownerName))
            return
        _ownerName.value = ownerName
    }

    fun setPassword(password: String) {
        if (!NewCardInputValidator.validatePassword(password))
            return
        _password.value = password
    }
}