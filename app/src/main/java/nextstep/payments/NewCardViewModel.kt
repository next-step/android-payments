package nextstep.payments

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.BankType
import nextstep.payments.data.Card
import nextstep.payments.data.PaymentCardsRepository

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

    private val _cardAdded = MutableStateFlow(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _selectedBankType = MutableStateFlow(BankType.NOT_SELECTED)
    val selectedBankType: StateFlow<BankType> = _selectedBankType.asStateFlow()

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

    fun setSelectBankType(bankType: BankType) {
        _selectedBankType.value = bankType
    }

    fun addCard() {
        repository.addCard(
            Card(
                cardNumber = cardNumber.value,
                expiredDate = expiredDate.value,
                ownerName = ownerName.value,
                password = password.value,
                bankType = selectedBankType.value
            )
        )
        _cardAdded.value = true
    }
}
