package nextstep.payments.screen.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.data.model.CreditCard

class NewCardViewModel : ViewModel() {

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _cardAdded = MutableStateFlow(NewCardEvent.Pending)
    val cardAdded : StateFlow<NewCardEvent> = _cardAdded.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        if(cardNumber.length > 16) return
        _cardNumber.value = cardNumber
    }

    fun setExpiredDate(expiredDate: String) {
        if(expiredDate.length > 4) return
        _expiredDate.value = expiredDate
    }

    fun setOwnerName(ownerName: String) {
        _ownerName.value = ownerName
    }

    fun setPassword(password: String) {
        if(password.length > 4) return
        _password.value = password
    }

    fun addCard(){
        PaymentCardsRepository.addCard(
            CreditCard(
                cardNumber = cardNumber.value,
                expiredDate = expiredDate.value,
                ownerName = ownerName.value,
                password = password.value
            )
        )
        _cardAdded.value = NewCardEvent.Success
    }

    fun cancelToAddCard(){
        _cardAdded.value = NewCardEvent.Cancel
    }
}
