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
        if (cardNumber.length > 16) return
        _cardNumber.value = cardNumber
    }

    fun setExpiredDate(expiredDate: String) {
        if (expiredDate.length > 4) return
        _expiredDate.value = expiredDate
    }

    fun setOwnerName(ownerName: String) {
        if (ownerName.length > 30) return
        _ownerName.value = ownerName
    }

    fun setPassword(password: String) {
        _password.value = password
    }
}