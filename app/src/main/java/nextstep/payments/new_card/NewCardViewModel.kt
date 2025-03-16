package nextstep.payments.new_card

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.CardRepository
import nextstep.payments.model.CardCompany

class NewCardViewModel(
    private val cardRepository: CardRepository = CardRepository
) : ViewModel() {

    private val _state = MutableStateFlow(NewCardState())
    val state: StateFlow<NewCardState> = _state.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _state.update {
            it.copy(card = it.card.copy(number = cardNumber))
        }
    }

    fun setExpiredDate(dueDate: String) {
        _state.update {
            it.copy(card = it.card.copy(dueDate = dueDate))
        }
    }

    fun setOwnerName(ownerName: String) {
        _state.update {
            it.copy(card = it.card.copy(name = ownerName))
        }
    }

    fun setPassword(password: String) {
        _state.update {
            it.copy(card = it.card.copy(password = password))
        }
    }

    fun setCompany(company: CardCompany) {
        _state.update {
            it.copy(card = it.card.copy(company = company))
        }
    }

    fun addCard() {
        cardRepository.addCard(card = _state.value.card)
    }
}
