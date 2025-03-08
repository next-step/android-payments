package nextstep.payments.screens.card.update

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.InMemoryPaymentCardsRepository
import nextstep.payments.domain.Card
import nextstep.payments.domain.PaymentCardsRepository
import nextstep.payments.screens.card.mapper.toDomain
import nextstep.payments.screens.card.state.CardCompanyState
import nextstep.payments.screens.card.state.CardState

class UpdateCardViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = InMemoryPaymentCardsRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<UpdateCardUiState> =
        MutableStateFlow(UpdateCardUiState.AddCardUiState())
    val uiState: StateFlow<UpdateCardUiState> = _uiState.asStateFlow()

    private val cardForEdit = MutableStateFlow<CardState?>(null)

    fun setEditCardMode(cardId: Int) {
        cardForEdit.update { paymentCardsRepository.findCardById(cardId)?.toState() }

        cardForEdit.value?.let { card->
            _uiState.update {
                UpdateCardUiState.EditCardUiState(
                    cardState = card ,
                    isFormValid = false,
                )
            }
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
                addCard(
                    numbers = state.cardNumber,
                    expiredDate = state.expiredDate,
                    ownerName = state.ownerName,
                    password = state.password,
                    cardCompany = state.selectedCardCompany?.toDomain() ?: return
                )
            }

            is UpdateCardUiState.EditCardUiState -> {
                editCard(
                    newCard = state.cardState
                )
            }
        }
    }

    private fun addCard(
        numbers: String,
        expiredDate: String,
        ownerName: String,
        password: String,
        cardCompany: CardCompany,
    ) {
        paymentCardsRepository.addCard(
            numbers = numbers,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password,
            cardCompany = cardCompany,
        )
        _uiState.update { state ->
            (state as UpdateCardUiState.AddCardUiState).copy(cardUpdated = true)
        }
    }

    private fun editCard(newCard: CardState) {
        paymentCardsRepository.updateCard(newCard.toDomain() ?: return)
        _uiState.update { state ->
            (state as UpdateCardUiState.EditCardUiState).copy(cardUpdated = true)
        }
    }

    companion object {
        private const val MAX_CARD_NUMBER_LENGTH = 16
        private const val MAX_EXPIRED_DATE_LENGTH = 4
        private const val MAX_OWNER_NAME_LENGTH = 30
        private const val MAX_PASSWORD_LENGTH = 4
    }
}
