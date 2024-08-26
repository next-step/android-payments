package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.model.Card
import nextstep.payments.repository.PaymentCardsRepository


class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {
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

    fun addCard() {
        val number = _cardNumber.value
        val date = _expiredDate.value
        val cardPassword = _password.value

        if (number.isNotBlank() &&
            date.isNotBlank() &&
            cardPassword.isNotBlank()
        ) {
            repository.addCard(
                Card(
                    cardNumber = number,
                    cardOwnerName = _ownerName.value,
                    cardExpiredDate = date
                )
            )
        }

        _cardAdded.value = true
    }

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
