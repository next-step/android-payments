package nextstep.payments.screens.card.update

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.InMemoryPaymentCardsRepository
import nextstep.payments.domain.Card
import nextstep.payments.domain.PaymentCardsRepository
import nextstep.payments.screens.card.CardCompanyState
import nextstep.payments.screens.card.mapper.toDomain

class UpdateCardViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = InMemoryPaymentCardsRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UpdateCardUiState> =
        MutableStateFlow(UpdateCardUiState.AddCardUiState())
    val uiState: StateFlow<UpdateCardUiState> = _uiState.asStateFlow()

    fun setEditCardMode(card: Card) {
        _uiState.update {
            UpdateCardUiState.EditCardUiState(cardForEdit = card)
        }
    }

    fun setSelectedCardCompany(newSelectedCardCompany: CardCompanyState) {
        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> _uiState.update {
                state.copy(cardState = state.cardState.copy(selectedCardCompany = newSelectedCardCompany))
            }

            is UpdateCardUiState.EditCardUiState -> _uiState.update {
                state.copy(cardState = state.cardState.copy(selectedCardCompany = newSelectedCardCompany))
            }
        }
    }

    fun setCardNumber(newCardNumber: String) {
        if (newCardNumber.length > MAX_CARD_NUMBER_LENGTH) return

        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> _uiState.update {
                state.copy(cardState = state.cardState.copy(cardNumber = newCardNumber))
            }

            is UpdateCardUiState.EditCardUiState -> _uiState.update {
                state.copy(cardState = state.cardState.copy(cardNumber = newCardNumber))
            }
        }
    }

    fun setExpiredDate(newExpiredDate: String) {
        if (newExpiredDate.length > MAX_EXPIRED_DATE_LENGTH) return

        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> _uiState.update {
                state.copy(cardState = state.cardState.copy(expiredDate = newExpiredDate))
            }

            is UpdateCardUiState.EditCardUiState -> _uiState.update {
                state.copy(cardState = state.cardState.copy(expiredDate = newExpiredDate))
            }
        }
    }

    fun setOwnerName(newOwnerName: String) {
        if (newOwnerName.length > MAX_OWNER_NAME_LENGTH) return

        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> _uiState.update {
                state.copy(cardState = state.cardState.copy(ownerName = newOwnerName))
            }

            is UpdateCardUiState.EditCardUiState -> _uiState.update {
                state.copy(cardState = state.cardState.copy(ownerName = newOwnerName))
            }
        }
    }

    fun setPassword(newPassword: String) {
        if (newPassword.length > MAX_PASSWORD_LENGTH) return

        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> _uiState.update {
                state.copy(cardState = state.cardState.copy(password = newPassword))
            }

            is UpdateCardUiState.EditCardUiState -> _uiState.update {
                state.copy(cardState = state.cardState.copy(password = newPassword))
            }
        }
    }

    fun updateCard() {
        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> {
                addCard(card = state.cardState.toDomain() ?: return)
            }

            is UpdateCardUiState.EditCardUiState -> {
                editCard(
                    oldCard = state.cardForEdit,
                    newCard = state.cardState.toDomain() ?: return
                )
            }
        }
    }

    private fun editCard(oldCard: Card, newCard: Card) {
        paymentCardsRepository.updateCard(oldCard = oldCard, newCard = newCard)
        _uiState.update { state ->
            (state as UpdateCardUiState.EditCardUiState).copy(cardUpdated = true)
        }
    }

    private fun addCard(card: Card) {
        paymentCardsRepository.addCard(card = card)
        _uiState.update { state ->
            (state as UpdateCardUiState.AddCardUiState).copy(cardUpdated = true)
        }
    }

    companion object {
        private const val MAX_CARD_NUMBER_LENGTH = 16
        private const val MAX_EXPIRED_DATE_LENGTH = 4
        private const val MAX_OWNER_NAME_LENGTH = 30
        private const val MAX_PASSWORD_LENGTH = 4
    }
}
