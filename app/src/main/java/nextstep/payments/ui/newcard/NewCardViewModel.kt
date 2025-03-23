package nextstep.payments.ui.newcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.model.CreditCardType.RegisteredCard
import nextstep.payments.ui.newcard.model.CardCompany
import nextstep.payments.ui.newcard.model.NewCardUiState

class NewCardViewModel(private val repository: PaymentCardsRepository = PaymentCardsRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(NewCardUiState())
    val uiState: StateFlow<NewCardUiState> = _uiState.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        _uiState.value = _uiState.value.copy(cardNumber = cardNumber)
    }

    fun setExpiredDate(expiredDate: String) {
        _uiState.value = _uiState.value.copy(expiredDate = expiredDate)
    }

    fun setOwnerName(ownerName: String) {
        _uiState.value = _uiState.value.copy(ownerName = ownerName)
    }

    fun setPassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }

    fun updateCardName(cardCompany: CardCompany) {
        _uiState.value = _uiState.value.copy(selectedCard = cardCompany)
    }

    fun showBottomSheet() {
        _uiState.value = _uiState.value.copy(showSelectCardBottomSheet = true)
    }

    fun hideBottomSheet() {
        _uiState.value = _uiState.value.copy(showSelectCardBottomSheet = false)
    }


    fun registerCard() {
        val currentUiState = _uiState.value
        when {
            currentUiState.cardNumber.length != 16 -> {
                _uiState.value = currentUiState.copy(inputInValidMessage = "카드 번호는 16자리여야 합니다.")
            }

            currentUiState.expiredDate.length != 4 -> {
                _uiState.value = currentUiState.copy(inputInValidMessage = "만료일은 4자리여야 합니다.")
            }

            currentUiState.password.length != 4 -> {
                _uiState.value = currentUiState.copy(inputInValidMessage = "비밀번호는 4자리여야 합니다.")
            }

            else -> {
                repository.addCard(
                    RegisteredCard(
                        number = currentUiState.cardNumber,
                        expiredDate = currentUiState.expiredDate,
                        ownerName = currentUiState.ownerName,
                        password = currentUiState.password,
                        cardCompany = currentUiState.selectedCard
                    )
                )
                _uiState.value = currentUiState.copy(cardAdded = true)
            }
        }
    }
}
