package nextstep.payments.ui.new

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.Bank
import nextstep.payments.data.BankType
import nextstep.payments.data.Card
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.state.CardUiState

class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _cardAdded = MutableStateFlow<Boolean>(false)
    val cardAdded: StateFlow<Boolean> = _cardAdded.asStateFlow()

    private val _cardUiState = MutableStateFlow<CardUiState>(CardUiState.Empty)
    val cardUiState: StateFlow<CardUiState> = _cardUiState

    fun addCard(card: Card) {
        repository.addCard(card)
        _cardAdded.value = true
    }

    fun fetchCards() {
        val cards = repository.cards
        _cardUiState.value = when {
            cards.isEmpty() -> CardUiState.Empty
            cards.size == 1 -> CardUiState.One(cards.first())
            else -> CardUiState.Many(cards)
        }
    }

    private val _cardCompany = MutableStateFlow("")
    val cardCompany: StateFlow<String> = _cardCompany.asStateFlow()

    private val _cardColor = MutableStateFlow(Color.Black)
    val cardColor: StateFlow<Color> = _cardColor.asStateFlow()

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    private val _bankType = MutableStateFlow(BankType.NOT_SELECTED)
    val bankType: StateFlow<BankType> = _bankType.asStateFlow()

    fun setCardCompany(cardCompany: String) {
        _cardCompany.value = cardCompany
    }

    fun setCardColor(color: Color) {
        _cardColor.value = color
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

    fun setBankType(bankType: BankType) {
        _bankType.value = bankType
    }

    fun modifyCard(card: Card) {
        repository.modifyCard(card)
        _cardAdded.value = true
    }
}
