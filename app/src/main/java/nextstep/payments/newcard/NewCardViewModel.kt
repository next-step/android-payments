package nextstep.payments.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.PaymentCardsRepository
import nextstep.payments.common.model.Card
import nextstep.payments.newcard.model.NewCardUiState

class NewCardViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewCardUiState())
    val uiState: StateFlow<NewCardUiState> = _uiState.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _uiState.update { prev ->
            prev.copy(
                cardNumber = cardNumber,
            )
        }
    }

    fun setExpiredDate(expiredDate: String) {
        _uiState.update { prev ->
            prev.copy(
                expiredDate = expiredDate,
            )
        }
    }

    fun setOwnerName(ownerName: String) {
        _uiState.update { prev ->
            prev.copy(ownerName = ownerName)
        }
    }

    fun setPassword(password: String) {
        _uiState.update { prev ->
            prev.copy(password = password)
        }
    }

    fun addCard() {
        paymentCardsRepository.addCard(
            Card(
                cardNumber = _uiState.value.cardNumber,
                expiredDate = _uiState.value.expiredDate,
                ownerName = _uiState.value.ownerName,
                password = _uiState.value.password
            )
        )
    }
}
