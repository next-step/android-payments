package nextstep.payments.feature.editcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.repository.PaymentCardsRepository

class EditCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _isEdited = MutableStateFlow(false)
    val isEdited: StateFlow<Boolean> = _isEdited.asStateFlow()

    private val _card: MutableStateFlow<Card> = MutableStateFlow(Card.mock)
    val card: StateFlow<Card> = _card.asStateFlow()

    init {
        val card = repository.getSelectCard()
        if (card != null) {
            _card.value = card
        }
    }

    fun editCard() {
        repository.editCard(
            Card(
                number = card.value.number,
                expiredDate = card.value.expiredDate,
                ownerName = card.value.ownerName,
                password = card.value.password,
                bankType = card.value.bankType
            )
        )
    }

    fun setCardNumber(cardNumber: String) {
        _isEdited.value = true
        _card.value = card.value.copy(number = cardNumber)
    }

    fun setExpiredDate(expiredDate: String) {
        _isEdited.value = true
        _card.value = card.value.copy(expiredDate = expiredDate)
    }

    fun setOwnerName(ownerName: String) {
        _isEdited.value = true
        _card.value = card.value.copy(ownerName = ownerName)
    }

    fun setPassword(password: String) {
        _isEdited.value = true
        _card.value = card.value.copy(password = password)
    }

    fun setBankType(bankType: BankType) {
        _isEdited.value = true
        _card.value = card.value.copy(bankType = bankType)
    }

}
