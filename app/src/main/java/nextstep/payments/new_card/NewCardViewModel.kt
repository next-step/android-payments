package nextstep.payments.new_card

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.BankType
import nextstep.payments.repository.PaymentCardsRepository

class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {
    private val _cardAdded = MutableStateFlow(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _bankType = MutableStateFlow<BankType?>(null)
    val bankType: StateFlow<BankType?> = _bankType.asStateFlow()

    private val _isBottomSheetOpen = MutableStateFlow(true)
    val isBottomSheetOpen: StateFlow<Boolean> = _isBottomSheetOpen.asStateFlow()

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

    fun setBankType(bankType: BankType) {
        _bankType.value = bankType
    }

    fun setBottomSheetOpen(isOpen: Boolean) {
        _isBottomSheetOpen.value = isOpen
    }

    fun addCard() {
        if (cardNumber.value.isEmpty() || expiredDate.value.isEmpty() || ownerName.value.isEmpty() || password.value.isEmpty() || bankType.value == null) {
            return
        }

        repository.addCard(
            cardNumber = cardNumber.value,
            expiredDate = expiredDate.value,
            ownerName = ownerName.value,
            password = password.value,
            bankType = bankType.value!!
        )

        _cardAdded.value = true
    }
}
