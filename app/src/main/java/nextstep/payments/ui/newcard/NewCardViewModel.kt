package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.constants.DateConstants.YEAR_DATE_FORMAT
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.Card
import nextstep.payments.model.BankType
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _selectBank = MutableStateFlow(BankType.NOT_SELECTED)
    val selectBank: StateFlow<BankType> = _selectBank.asStateFlow()

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

    fun setCardNumber(cardNumber: String) {
        _cardNumber.value = cardNumber.take(16)
    }

    fun setExpiredDate(expiredDate: String) {
        _expiredDate.value = expiredDate.take(4)
    }

    fun setOwnerName(ownerName: String) {
        _ownerName.value = ownerName
    }

    fun setPassword(password: String) {
        _password.value = password.take(4)
    }

    fun setBankType(bankType: BankType) {
        _selectBank.value = bankType
    }

    fun addCard() {
        val toYearMonth = YearMonth.parse(
            expiredDate.value,
            DateTimeFormatter.ofPattern(YEAR_DATE_FORMAT)
        )

        repository.addCard(
            Card(
                type = selectBank.value,
                number = cardNumber.value,
                expiredDate = toYearMonth,
                ownerName = ownerName.value,
                password = password.value
            )
        )
        _cardAdded.value = true
    }
}
