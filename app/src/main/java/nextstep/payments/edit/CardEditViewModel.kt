package nextstep.payments.edit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.CardRepository
import nextstep.payments.model.CreditCard

class CardEditViewModel(
    private val cardRepository: CardRepository = CardRepository
) : ViewModel() {

    private val _cardEditState = MutableStateFlow(CardEditState())
    val cardEditState: StateFlow<CardEditState> = _cardEditState.asStateFlow()

    fun initializeCard(card: CreditCard) {
        _cardEditState.value = CardEditState(
            originalCard = card,
            editCard = card,
        )
    }

    fun setNumber(cardNumber: String) {
        _cardEditState.value = _cardEditState.value.copy(
            editCard = CreditCard.emptyCard.copy(
                number = cardNumber
            )
        )
    }

    fun setDueDate(dueDate: String) {
        _cardEditState.value = _cardEditState.value.copy(
            editCard = CreditCard.emptyCard.copy(
                dueDate = dueDate
            )
        )
    }

    fun setName(name: String) {
        _cardEditState.value = _cardEditState.value.copy(
            editCard = CreditCard.emptyCard.copy(
                name = name
            )
        )
    }

    fun setPassword(password: String) {
        _cardEditState.value = _cardEditState.value.copy(
            editCard = CreditCard.emptyCard.copy(
                password = password
            )
        )
    }

    fun editCard() {
        cardRepository.editCard(
            card = _cardEditState.value.editCard
        )
    }
}