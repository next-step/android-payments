package nextstep.payments.edit

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.CardRepository

class CardEditViewModel(
    private val cardRepository: CardRepository = CardRepository
) : ViewModel() {

    private val _cardEditState = MutableStateFlow(CardEditState())
    val cardEditState: StateFlow<CardEditState> = _cardEditState.asStateFlow()

    fun initializeCard(cardId: String) {
        val card = cardRepository.getCard(cardId)
        _cardEditState.value = CardEditState(
            originalCard = card,
            editCard = card,
        )
    }

    fun setNumber(cardNumber: String) {
        _cardEditState.update {
            it.copy(
                editCard = it.editCard.copy(
                    number = cardNumber
                )
            )
        }
    }

    fun setDueDate(dueDate: String) {
        _cardEditState.update {
            it.copy(
                editCard = it.editCard.copy(
                    dueDate = dueDate
                )
            )
        }
    }

    fun setName(name: String) {
        _cardEditState.update {
            it.copy(
                editCard = it.editCard.copy(
                    name = name
                )
            )
        }
    }

    fun setPassword(password: String) {
        _cardEditState.update {
            it.copy(
                editCard = it.editCard.copy(
                    password = password
                )
            )
        }
    }

    fun editCard() {
        cardRepository.editCard(
            card = _cardEditState.value.editCard
        )
    }
}
