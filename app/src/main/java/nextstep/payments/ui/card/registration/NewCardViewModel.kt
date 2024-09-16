package nextstep.payments.ui.card.registration

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.BankType
import nextstep.payments.data.Card
import nextstep.payments.data.PaymentCardsRepository

class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository,
    private val savedStateHandle: SavedStateHandle
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

    init {
        openCardData()
    }

    fun openCardData() {
        savedStateHandle.get<String>(KEY_CARD_NUMBER)?.let {
            _cardNumber.value = it
        }
        savedStateHandle.get<String>(KEY_EXPIRED)?.let {
            _expiredDate.value = it
        }

        savedStateHandle.get<String>(KEY_OWNER_NAME)?.let {
            _ownerName.value = it
        }

        savedStateHandle.get<String>(KEY_PASSWORD)?.let {
            _password.value = it
        }

        savedStateHandle.get<BankType>(KEY_BANK_TYPE)?.let {
            _selectedBankType.value = it
        }

        savedStateHandle.get<RegistrationUiState>(KEY_UI_STATE)?.let {
            _uiState.value = it
        }
        savedStateHandle.get<Card>(KEY_OLD_CARD)?.let {
            oldCard = it
        }
    }

    fun saveCardData() {
        savedStateHandle[KEY_CARD_NUMBER] = cardNumber.value
        savedStateHandle[KEY_EXPIRED] = expiredDate.value
        savedStateHandle[KEY_OWNER_NAME] = ownerName.value
        savedStateHandle[KEY_PASSWORD] = password.value
        savedStateHandle[KEY_BANK_TYPE] = selectedBankType.value
        savedStateHandle[KEY_UI_STATE] = uiState.value
        savedStateHandle[KEY_OLD_CARD] = oldCard
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
        if (savedStateHandle.get<Card>(KEY_OLD_CARD) == null) {
            _cardNumber.value = card.cardNumber
            _expiredDate.value = card.expiredDate
            _ownerName.value = card.ownerName
            _password.value = card.password
            _selectedBankType.value = card.bankType
        }
        oldCard = card
    }

    fun setUiState(newUiState: RegistrationUiState) {
        if(savedStateHandle.get<RegistrationUiState>(KEY_UI_STATE) == null) {
            _uiState.value = newUiState
        }
    }

    companion object {
        private const val TAG = "NewCardViewModel"
        private const val KEY_CARD_NUMBER = "cardNumber"
        private const val KEY_EXPIRED = "expired"
        private const val KEY_OWNER_NAME = "ownerName"
        private const val KEY_PASSWORD = "password"
        private const val KEY_BANK_TYPE = "bankType"
        private const val KEY_UI_STATE = "uiState"
        private const val KEY_OLD_CARD = "oldCard"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val savedStateHandle = createSavedStateHandle()
                NewCardViewModel(
                    repository = PaymentCardsRepository,
                    savedStateHandle = savedStateHandle
                )
            }
        }
    }
}
