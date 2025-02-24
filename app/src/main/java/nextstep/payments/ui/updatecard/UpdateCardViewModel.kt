package nextstep.payments.ui.updatecard

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.constants.DateConstants.YEAR_DATE_FORMAT
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ext.getSerializable
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class UpdateCardViewModel(
    savedStateHandle: SavedStateHandle,
    private val paymentCardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val getCard = requireNotNull(
        savedStateHandle.getSerializable<Card>(KEY_CARD_ITEM)
    ) { "Card is Null" }

    private val _selectedBank = MutableStateFlow(BankType.NOT_SELECTED)
    val selectedBank: StateFlow<BankType> = _selectedBank.asStateFlow()

    private val _cardUpdated = MutableStateFlow(false)
    val cardUpdated: StateFlow<Boolean> = _cardUpdated.asStateFlow()

    private val _cardNumber = MutableStateFlow("")
    val cardNumber: StateFlow<String> = _cardNumber.asStateFlow()

    private val _expiredDate = MutableStateFlow("")
    val expiredDate: StateFlow<String> = _expiredDate.asStateFlow()

    private val _ownerName = MutableStateFlow("")
    val ownerName: StateFlow<String> = _ownerName.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    init {
        _selectedBank.value = getCard.type
        _cardNumber.value = getCard.number
        _expiredDate.value = getCard.getStringExpiredDate()
        _ownerName.value = getCard.ownerName
        _password.value = getCard.password
    }

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

    fun updateCard() {
        val toYearMonth = YearMonth.parse(
            expiredDate.value,
            DateTimeFormatter.ofPattern(YEAR_DATE_FORMAT)
        )
        val card = Card(
            type = selectedBank.value,
            number = cardNumber.value,
            expiredDate = toYearMonth,
            ownerName = ownerName.value,
            password = password.value
        )

        if (paymentCardsRepository.upsertCard(card)) {
            _cardUpdated.value = true
        }
    }

    companion object {

        const val KEY_CARD_ITEM = "key_card_Item"

        fun createViewModelFactory(repository: PaymentCardsRepository = PaymentCardsRepository) =
            object : AbstractSavedStateViewModelFactory() {
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    if (modelClass.isAssignableFrom(UpdateCardViewModel::class.java)) {
                        return UpdateCardViewModel(handle, repository) as T
                    } else throw IllegalArgumentException()
                }
            }
    }
}