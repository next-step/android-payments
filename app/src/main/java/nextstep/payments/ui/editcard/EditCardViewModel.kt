package nextstep.payments.ui.editcard

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.payments.model.card.CardNumber
import nextstep.payments.model.card.CreditCard
import nextstep.payments.repository.PaymentCardsRepository
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.event.UiEvent
import java.time.YearMonth
import java.time.format.DateTimeFormatter

internal class EditCardViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    val targetCard: CreditCard =
        savedStateHandle.get<CreditCard>(EditCardActivity.EXTRA_KEY)
            ?: error("뷰모델에서 CreditCard를 가져오지 못했습니다")

    private val _uiState: MutableStateFlow<EditCardUiState> = MutableStateFlow(targetCard.toUi())
    val uiState = _uiState.asStateFlow()

    fun setCardNumber(cardNumber: String) {
        if (cardNumber.length > 16) return
        _uiState.update { it.copy(cardNumber = cardNumber.filter(Char::isDigit)) }
    }

    fun setExpiredDate(expirationDate: String) {
        if (expirationDate.length > 4) return
        _uiState.update { it.copy(expirationDate = expirationDate.filter(Char::isDigit)) }
    }

    fun setOwnerName(ownerName: String) {
        _uiState.update { it.copy(ownerName = ownerName) }
    }

    fun setPassword(password: String) {
        if (password.length > 4) return
        _uiState.update { it.copy(password = password.filter(Char::isDigit)) }
    }

    fun updateCard() {
        val currentUiState = _uiState.value
        if (targetCard.toUi() == currentUiState) return

        viewModelScope.launch {
            runCatching {
                val cardNumbers = currentUiState.cardNumber.chunked(4).map { CardNumber(it) }
                val expirationDate =
                    YearMonth.parse(
                        currentUiState.expirationDate,
                        DateTimeFormatter.ofPattern("MMyy")
                    )

                CreditCard(
                    id = targetCard.id,
                    cardNumbers = cardNumbers,
                    expirationDate = expirationDate,
                    password = currentUiState.password,
                    ownerName = currentUiState.ownerName,
                    bankType = currentUiState.bank.bankType
                )
            }.onSuccess { updatedCard ->
                repository.updateCard(targetCard, updatedCard)
                _uiState.update { state ->
                    state.copy(
                        isUpdated = UiEvent(
                            data = Unit,
                            onProcessed = { _uiState.update { it.copy(isUpdated = null) } })
                    )
                }
            }.onFailure { e ->
                Log.e("EditCard", "Error edit card: ${e.message}")
                _uiState.update { state ->
                    state.copy(
                        isError = UiEvent(
                            data = e,
                            onProcessed = { _uiState.update { it.copy(isError = null) } }
                        )
                    )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras,
            ): T {
                val savedStateHandle = extras.createSavedStateHandle()

                return EditCardViewModel(
                    savedStateHandle = savedStateHandle,
                    repository = PaymentCardsRepository
                ) as T
            }
        }
    }
}

private fun CreditCard.toUi(): EditCardUiState {
    val formatter = DateTimeFormatter.ofPattern("MMyy")
    val formattedDate = expirationDate.format(formatter)

    return EditCardUiState(
        cardNumber = this.cardNumbers.joinToString(separator = "") { it.number },
        ownerName = this.ownerName,
        expirationDate = formattedDate,
        password = password,
        bank = CardBankInformation.from(bankType)
    )
}