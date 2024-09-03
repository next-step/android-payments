package nextstep.payments.ui.newcard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.repository.PaymentCardsRepository


class NewCardViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val savedCard = savedStateHandle.get<Card>("card")

    private val _canEdit = MutableStateFlow(savedCard != null)
    val canEdit: StateFlow<Boolean> = _canEdit.asStateFlow()

    private val _cardNumber = MutableStateFlow(savedCard?.cardNumber.orEmpty())
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow(savedCard?.cardExpiredDate.orEmpty())
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow(savedCard?.cardOwnerName.orEmpty())
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow(savedCard?.cardPassword.orEmpty())
    val password: StateFlow<String> = _password.asStateFlow()

    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _bankTypes = MutableStateFlow<List<BankType>>(emptyList())
    val bankTypes: StateFlow<List<BankType>> = _bankTypes.asStateFlow()

    private val _selectedBank = MutableStateFlow<BankType?>(savedCard?.bankType)
    val selectedBankType: StateFlow<BankType?> = _selectedBank.asStateFlow()

    private val _isCardModified = MutableStateFlow(false)
    val isCardModified: StateFlow<Boolean> = _isCardModified.asStateFlow()


    init {
        _bankTypes.value = BankType.entries
    }

    fun addCard() {
        if (isCardDataSavable()) {
            val id = PaymentCardsRepository.cards.size + 1
            _selectedBank.value?.let {
                PaymentCardsRepository.addCard(
                    Card(
                        id = id,
                        cardNumber = _cardNumber.value,
                        cardOwnerName = _ownerName.value,
                        cardExpiredDate = _expiredDate.value,
                        cardPassword = _password.value,
                        bankType = it
                    )
                )
                _cardAdded.value = true
            }
        }
    }

    fun modifyCard() {
        if (isCardDataSavable()) {
            val id = savedCard?.id
            _selectedBank.value?.let {
                val card = Card(
                    id = id,
                    cardNumber = _cardNumber.value,
                    cardOwnerName = _ownerName.value,
                    cardExpiredDate = _expiredDate.value,
                    cardPassword = _password.value,
                    bankType = it
                )
                PaymentCardsRepository.modifyCard(card)
                _cardAdded.value = true
            }
        }
    }

    private fun isCardDataSavable(): Boolean {
        val number = _cardNumber.value
        val date = _expiredDate.value
        val cardPassword = _password.value
        val currentBankType = _selectedBank.value

        return number.isNotBlank() &&
            date.isNotBlank() &&
            cardPassword.isNotBlank() &&
            currentBankType != null
    }

    private fun checkCardModification() {
        _isCardModified.value =
            (_cardNumber.value != savedCard?.cardNumber) ||
                (_expiredDate.value != savedCard.cardExpiredDate) ||
                (_ownerName.value != savedCard.cardOwnerName) ||
                (_password.value != savedCard.cardPassword) ||
                (_selectedBank.value != savedCard.bankType)
    }

    fun setCardNumber(cardNumber: String) {
        _cardNumber.value = cardNumber
        checkCardModification()
    }

    fun setExpiredDate(expiredDate: String) {
        _expiredDate.value = expiredDate
        checkCardModification()
    }

    fun setOwnerName(ownerName: String) {
        _ownerName.value = ownerName
        checkCardModification()
    }

    fun setPassword(password: String) {
        _password.value = password
        checkCardModification()
    }

    fun setSelectedBankType(bankType: BankType) {
        _selectedBank.value = bankType
        checkCardModification()
    }
}
