package nextstep.payments.ui.updatecard

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.updatecard.navigation.UpdateCard

class UpdateCardViewModel(
    savedStateHandle: SavedStateHandle,
    private val paymentCardsRepository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val getCard =
        requireNotNull(
            savedStateHandle.toRoute<UpdateCard>().decodeCard()
        ) { "Card is Null" }

    private val _uiState = MutableStateFlow(UpdateCardState())
    val uiState: StateFlow<UpdateCardState> = _uiState.asStateFlow()


    init {
        _uiState.update {
            it.copy(
                selectedBank = getCard.type,
                cardNumber = getCard.number,
                expiredDate = getCard.getStringExpiredDate(),
                ownerName = getCard.ownerName,
                password = getCard.password
            )
        }
    }

    fun setCardNumber(cardNumber: String) {
        _uiState.update {
            it.copy(cardNumber = cardNumber.take(16))
        }
    }

    fun setExpiredDate(expiredDate: String) {
        _uiState.update {
            it.copy(expiredDate = expiredDate.take(4))
        }
    }

    fun setOwnerName(ownerName: String) {
        _uiState.update {
            it.copy(ownerName = ownerName)
        }
    }

    fun setPassword(password: String) {
        _uiState.update {
            it.copy(password = password.take(4))
        }
    }

    fun updateCard() {
        if (paymentCardsRepository.upsertCard(_uiState.value.toCard())) {
            _uiState.update {
                it.copy(cardUpdated = true)
            }
        }
    }
}