package nextstep.payments.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.model.CreditCardType.CardInfo

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

    private val _cardAdded = MutableStateFlow(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _cardName = MutableStateFlow("")
    val cardName: StateFlow<String> = _cardName.asStateFlow()

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
        // TODO when 문으로 각각 분기하여 에러내보내기
        if (cardNumber.value.length == 16 && expiredDate.value.length == 4 && password.value.length == 4 && ownerName.value.isNotEmpty()) {
            repository.addCard(
                CardInfo(
                    number = cardNumber.value,
                    expiredDate = expiredDate.value,
                    ownerName = ownerName.value,
                    password = password.value,
                    cardName = cardName.value
                )
            )
            _cardAdded.value = true
        } else {
            //TODO TextField에 오류 표시
        }
    }
}
