package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.model.CreditCard
import nextstep.payments.repository.PaymentCardsRepository

class NewCardViewModel(private val repository: PaymentCardsRepository = PaymentCardsRepository) :
    ViewModel() {

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        if (cardNumber.length > MAX_CARD_NUMBER_LENGTH) return
        _cardNumber.value = cardNumber.filter { it.isDigit() }
    }

    fun setExpiredDate(expiredDate: String) {
        if (expiredDate.length > MAX_EXPIRED_DATE_LENGTH) return
        _expiredDate.value = expiredDate.filter { it.isDigit() }
    }

    fun setOwnerName(ownerName: String) {
        if (ownerName.length > MAX_OWNER_NAME_LENGTH) return
        _ownerName.value = ownerName.filter { it.isLetter() }
    }

    fun setPassword(password: String) {
        if (password.length > MAX_PASSWORD_LENGTH) return
        _password.value = password.filter { it.isDigit() }
    }

    fun onSaveClick() {
        repository.addCard(
            CreditCard(
                cardNumber = _cardNumber.value,
                expiredDate = _expiredDate.value,
                ownerName = _ownerName.value,
                password = _password.value
            )
        )
        _cardAdded.update { true }
    }

    companion object {
        const val MAX_CARD_NUMBER_LENGTH = 16
        const val MAX_EXPIRED_DATE_LENGTH = 4
        const val MAX_OWNER_NAME_LENGTH = 10
        const val MAX_PASSWORD_LENGTH = 4
    }
}
