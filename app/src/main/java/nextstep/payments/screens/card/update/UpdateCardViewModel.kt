package nextstep.payments.screens.card.update

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.InMemoryPaymentCardsRepository
import nextstep.payments.domain.PaymentCardsRepository
import nextstep.payments.screens.card.mapper.toDomain
import nextstep.payments.screens.card.mapper.toState
import nextstep.payments.screens.card.uistate.CardCompanyUiState
import nextstep.payments.screens.card.uistate.CardUiState

class UpdateCardViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = InMemoryPaymentCardsRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UpdateCardUiState> =
        MutableStateFlow(UpdateCardUiState.AddCardUiState())
    val uiState: StateFlow<UpdateCardUiState> = _uiState.asStateFlow()

    private val cardForEdit = MutableStateFlow<CardUiState?>(null)

    private val _cardUpdated = MutableStateFlow(false)
    val cardUpdated: StateFlow<Boolean> = _cardUpdated.asStateFlow()

    fun setEditCardMode(cardId: Int) {
        cardForEdit.update { paymentCardsRepository.findCardById(cardId)?.toState() }

        cardForEdit.value?.let { card->
            _uiState.update {
                UpdateCardUiState.EditCardUiState(
                    cardUiState = card ,
                    isFormValid = false,
                )
            }
        }
    }

    fun setSelectedCardCompany(newSelectedCardCompany: CardCompanyUiState) {
        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> _uiState.update {
                state.copy(
                    selectedCardCompany = newSelectedCardCompany,
                )
            }

            is UpdateCardUiState.EditCardUiState -> _uiState.update {
                val newCardState = state.cardUiState.copy(selectedCardCompany = newSelectedCardCompany)
                state.copy(
                    cardUiState = newCardState,
                    isFormValid = canEdit(newCardState),
                )
            }
        }
    }

    fun setCardNumber(newCardNumber: String) {
        if (newCardNumber.length > MAX_CARD_NUMBER_LENGTH) return

        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> _uiState.update {
                state.copy(cardNumber = newCardNumber)
            }

            is UpdateCardUiState.EditCardUiState -> _uiState.update {
                val newCardUiState = state.cardUiState.copy(cardNumber = newCardNumber)
                state.copy(
                    cardUiState = newCardUiState,
                    isFormValid = canEdit(newCardUiState),
                )
            }
        }
    }

    fun setExpiredDate(newExpiredDate: String) {
        if (newExpiredDate.length > MAX_EXPIRED_DATE_LENGTH) return

        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> _uiState.update {
                state.copy(expiredDate = newExpiredDate)
            }

            is UpdateCardUiState.EditCardUiState -> _uiState.update {
                val newCardUiState = state.cardUiState.copy(expiredDate = newExpiredDate)
                state.copy(
                    cardUiState = newCardUiState,
                    isFormValid = canEdit(newCardUiState),
                )
            }
        }
    }

    fun setOwnerName(newOwnerName: String) {
        if (newOwnerName.length > MAX_OWNER_NAME_LENGTH) return

        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> _uiState.update {
                state.copy(ownerName = newOwnerName)
            }

            is UpdateCardUiState.EditCardUiState -> _uiState.update {
                val newCardUiState = state.cardUiState.copy(ownerName = newOwnerName)
                state.copy(
                    cardUiState = newCardUiState,
                    isFormValid = canEdit(newCardUiState),
                )
            }
        }
    }

    fun setPassword(newPassword: String) {
        if (newPassword.length > MAX_PASSWORD_LENGTH) return

        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> _uiState.update {
                state.copy(password = newPassword)
            }

            is UpdateCardUiState.EditCardUiState -> _uiState.update {
                val newCardUiState = state.cardUiState.copy(password = newPassword)
                state.copy(
                    cardUiState = newCardUiState,
                    isFormValid = canEdit(newCardUiState),
                )
            }
        }
    }

    private fun canEdit(cardUiState: CardUiState):Boolean {
        return cardUiState.isFormValid() && cardUiState != cardForEdit.value
    }

    fun updateCard() {
        when (val state: UpdateCardUiState = _uiState.value) {
            is UpdateCardUiState.AddCardUiState -> {
                paymentCardsRepository.addCard(
                    numbers = state.cardNumber,
                    expiredDate = state.expiredDate,
                    ownerName = state.ownerName,
                    password = state.password,
                    cardCompany = state.selectedCardCompany?.toDomain() ?: return,
                )
            }

            is UpdateCardUiState.EditCardUiState -> {
                paymentCardsRepository.updateCard(
                    card = state.cardUiState.toDomain() ?: return,
                )
            }
        }

        _cardUpdated.update { true }
    }

    companion object {
        private const val MAX_CARD_NUMBER_LENGTH = 16
        private const val MAX_EXPIRED_DATE_LENGTH = 4
        private const val MAX_OWNER_NAME_LENGTH = 30
        private const val MAX_PASSWORD_LENGTH = 4
    }
}
