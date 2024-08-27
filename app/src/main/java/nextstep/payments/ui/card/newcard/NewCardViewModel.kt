package nextstep.payments.ui.card.newcard

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardRepository
import nextstep.payments.model.BankType
import nextstep.payments.model.Card

class NewCardViewModel(
    private val repository: PaymentCardRepository = PaymentCardRepository
) : ViewModel() {

    private val _cardAdded = MutableStateFlow(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _bankType: MutableStateFlow<BankType?> = MutableStateFlow(null)
    val bankType: StateFlow<BankType?> = _bankType.asStateFlow()

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    fun setBankType(bankType: BankType) {
        _bankType.value = bankType
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
        val bankType = bankType.value ?: run {
            Log.d("[NewCardViewModel]", "bankType cannot be null")
            return
        }

        val card = Card(
            bankType = bankType,
            cardNumber = cardNumber.value,
            expiredDate = expiredDate.value,
            ownerName = ownerName.value,
            password = password.value,
        )
        repository.addCard(card)
        _cardAdded.value = true
    }

}
