package nextstep.payments.screens.card.new

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.InMemoryPaymentCardsRepository
import nextstep.payments.domain.Card
import nextstep.payments.domain.PaymentCardsRepository
import nextstep.payments.screens.card.CardCompanyState
import nextstep.payments.screens.card.toDomain

class NewCardViewModel(
    private val paymentCardsRepository: PaymentCardsRepository = InMemoryPaymentCardsRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewCardUiState())
    val uiState: StateFlow<NewCardUiState> = _uiState.asStateFlow()

    fun setSelectedCardCompany(newSelectedCardCompany: CardCompanyState) {
        if (newSelectedCardCompany == _uiState.value.selectedCardCompany) return
        _uiState.update {
            it.copy(selectedCardCompany = newSelectedCardCompany)
        }
    }

    fun setCardNumber(newCardNumber: String) {
        if (newCardNumber.length > MAX_CARD_NUMBER_LENGTH) return
        _uiState.update {
            it.copy(cardNumber = newCardNumber)
        }
    }

    fun setExpiredDate(newExpiredDate: String) {
        if (newExpiredDate.length > MAX_EXPIRED_DATE_LENGTH) return
        _uiState.update {
            it.copy(expiredDate = newExpiredDate)
        }
    }

    fun setOwnerName(newOwnerName: String) {
        if (newOwnerName.length > MAX_OWNER_NAME_LENGTH) return
        _uiState.update {
            it.copy(ownerName = newOwnerName)
        }
    }

    fun setPassword(newPassword: String) {
        if (newPassword.length > MAX_PASSWORD_LENGTH) return
        _uiState.update {
            it.copy(password = newPassword)
        }
    }

    fun addCard() {
        paymentCardsRepository.addCard(
            Card(
                numbers = _uiState.value.cardNumber,
                expiredDate = _uiState.value.expiredDate,
                ownerName = _uiState.value.ownerName,
                password = _uiState.value.password,
                cardCompany = _uiState.value.selectedCardCompany?.toDomain() ?: return,
            )
        )
        _uiState.update {
            it.copy(cardAdded = true)
        }
    }

    companion object {
        private const val MAX_CARD_NUMBER_LENGTH = 16
        private const val MAX_EXPIRED_DATE_LENGTH = 4
        private const val MAX_OWNER_NAME_LENGTH = 30
        private const val MAX_PASSWORD_LENGTH = 4
    }
}
