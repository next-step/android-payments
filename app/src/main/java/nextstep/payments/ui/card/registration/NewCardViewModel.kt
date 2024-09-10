package nextstep.payments.ui.card.registration

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

    private var oldCard: Card? = null

    private val _uiState = MutableStateFlow<RegistrationUiState>(RegistrationUiState.NewCard)
    val uiState: StateFlow<RegistrationUiState> = _uiState.asStateFlow()


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

    fun saveCard() {
        when (uiState.value) {
            RegistrationUiState.NewCard -> addCard()
            RegistrationUiState.EditCard -> editCard()
        }
    }

    private fun addCard() {
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

    private fun editCard() {
        oldCard?.let {
            if (isCardDataChange()) {
                repository.editCard(
                    newCard = Card(
                        id = it.id,
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
    }

    private fun isCardDataChange(): Boolean {
        oldCard?.let {
            return it.cardNumber != cardNumber.value ||
                    it.expiredDate != expiredDate.value ||
                    it.ownerName != ownerName.value ||
                    it.password != password.value ||
                    it.bankType != selectedBankType.value
        } ?: return false
    }

    fun setOldCard(card: Card) {
        oldCard = card
        _cardNumber.value = card.cardNumber
        _expiredDate.value = card.expiredDate
        _ownerName.value = card.ownerName
        _password.value = card.password
        _selectedBankType.value = card.bankType
    }

    fun setUiState(newUiState: RegistrationUiState) {
        _uiState.value = newUiState
    }
}
