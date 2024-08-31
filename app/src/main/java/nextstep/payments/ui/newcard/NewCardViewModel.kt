package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.model.Card
import nextstep.payments.model.CardCompany

class NewCardViewModel (
    private val repository: PaymentCardsRepository = PaymentCardsRepository
): ViewModel() {
    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _selectedCard = MutableStateFlow<CardCompany>(CardCompany.NOT_SELECTED)
    val selectedCard: StateFlow<CardCompany> = _selectedCard.asStateFlow()

    private val _cardCompanies = MutableStateFlow<List<CardCompany>>(CardCompany.activeCards)
    val cardCompanies = _cardCompanies.asStateFlow()

    fun setCardCompany(cardCompany: CardCompany) {
        _selectedCard.value = cardCompany
    }

    fun addCard(card: Card) {
        repository.addCard(card)
        _cardAdded.value = true
    }

    fun setCardNumber(cardNumber: String) {
        if (cardNumber.length <= 16) {
            _cardNumber.value = cardNumber
        }
    }

    fun setExpiredDate(expiredDate: String) {
        if (expiredDate.length <= 4) {
            _expiredDate.value = expiredDate
        }
    }

    fun setOwnerName(ownerName: String) {
        if (ownerName.length <= 30) {
            _ownerName.value = ownerName
        }
    }

    fun setPassword(password: String) {
        if (password.length <= 4) {
            _password.value = password
        }
    }
}