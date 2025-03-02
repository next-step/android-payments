package nextstep.payments.screens.card.new

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.CardCompanyState
import nextstep.payments.data.InMemoryPaymentCardsRepository
import nextstep.payments.domain.Card
import nextstep.payments.domain.PaymentCardsRepository
import nextstep.payments.screens.card.toDomain

class NewCardViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = InMemoryPaymentCardsRepository,
) : ViewModel() {

    private val _selectedCardCompany = MutableStateFlow(CardCompanyState.NOT_SELECTED)
    val selectedCardCompany: StateFlow<CardCompanyState> = _selectedCardCompany.asStateFlow()

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

    fun setSelectedCardCompany(newSelectedCardCompany: CardCompanyState) {
        if (newSelectedCardCompany == _selectedCardCompany.value) return
        _selectedCardCompany.value = newSelectedCardCompany
    }

    fun setCardNumber(newCardNumber: String) {
        if (newCardNumber.length > MAX_CARD_NUMBER_LENGTH) return
        _cardNumber.value = newCardNumber
    }

    fun setExpiredDate(newExpiredDate: String) {
        if (newExpiredDate.length > MAX_EXPIRED_DATE_LENGTH) return
        _expiredDate.value = newExpiredDate
    }

    fun setOwnerName(newOwnerName: String) {
        if (newOwnerName.length > MAX_OWNER_NAME_LENGTH) return
        _ownerName.value = newOwnerName
    }

    fun setPassword(newPassword: String) {
        if (newPassword.length > MAX_PASSWORD_LENGTH) return
        _password.value = newPassword
    }

    fun addCard() {
        paymentCardsRepository.addCard(
            Card(
                numbers = cardNumber.value,
                expiredDate = expiredDate.value,
                ownerName = ownerName.value,
                password = password.value,
                cardCompany = selectedCardCompany.value.toDomain(),
            )
        )
        _cardAdded.value = true
    }

    companion object {
        private const val MAX_CARD_NUMBER_LENGTH = 16
        private const val MAX_EXPIRED_DATE_LENGTH = 4
        private const val MAX_OWNER_NAME_LENGTH = 30
        private const val MAX_PASSWORD_LENGTH = 4
    }
}
