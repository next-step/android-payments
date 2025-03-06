package nextstep.payments.new_card

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.CardRepository
import nextstep.payments.model.CardCompany
import nextstep.payments.model.CreditCard

class NewCardViewModel(
    private val cardRepository: CardRepository = CardRepository
) : ViewModel() {

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _company = MutableStateFlow(CardCompany.NONE)
    val company: StateFlow<CardCompany> = _company.asStateFlow()

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

    fun setCompany(company: CardCompany) {
        _company.value = company
    }

    fun addCard() {
        cardRepository.addCard(
            CreditCard(
                number = cardNumber.value,
                dueDate = expiredDate.value,
                name = ownerName.value,
                password = password.value,
                company = company.value
            )
        )
    }
}
