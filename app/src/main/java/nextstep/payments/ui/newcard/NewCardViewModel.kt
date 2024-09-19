package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import nextstep.payments.data.Card
import nextstep.payments.data.PaymentCardsRepository

class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository,
) : ViewModel() {
    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    val isValidCard =
        combine(cardNumber, password, expiredDate) { cardNumber, password, expiredDate ->
            cardNumber.length == 16 && password.length == 4 && expiredDate.length == 4
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

    fun addCard() {
        repository.addCard(
            Card(
                cardId = repository.cards.size.toLong(),
                cardNumber = cardNumber.value,
                expiredDate = expiredDate.value,
                ownerName = ownerName.value,
                password = password.value,
            )
        )
    }
}
