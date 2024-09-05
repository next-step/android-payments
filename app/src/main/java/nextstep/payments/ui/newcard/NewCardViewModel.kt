package nextstep.payments.ui.newcard

import android.util.Log
import androidx.compose.ui.focus.FocusDirection
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.payments.model.card.CardNumber
import nextstep.payments.model.card.CreditCard
import nextstep.payments.repository.PaymentCardsRepository
import nextstep.payments.ui.component.card.CardBankInformation
import java.time.YearMonth
import java.time.format.DateTimeFormatter

/**
 * NewCardViewModel은 새 결제 카드를 관리하고 추가하는 로직을 포함한 ViewModel입니다.
 * 카드 정보는 PaymentCard 데이터 클래스로 관리되며, UI에서 사용할 상태 값들은 StateFlow를 통해 제공됩니다.
 */
internal class NewCardViewModel(
    private val repository: PaymentCardsRepository = PaymentCardsRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(NewCardUiState.default)
    val uiState = _uiState.asStateFlow()

    // 카드를 성공적으로 추가했는지 여부를 나타내는 상태.
    private val _cardAdded = MutableSharedFlow<Boolean>()
    val cardAdded = _cardAdded.asSharedFlow()

    // 포커스 이동 명령
    private val _commandFocus = MutableSharedFlow<FocusDirection>()
    val commandFocus = _commandFocus.asSharedFlow()

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    fun setCardNumber(cardNumber: String) {
        if (cardNumber.length > 16) return
        _uiState.update { it.copy(cardNumber = cardNumber.filter(Char::isDigit)) }
        if (cardNumber.length == 16) {
            viewModelScope.launch { _commandFocus.emit(FocusDirection.Next) }
        }
    }

    fun setExpiredDate(expirationDate: String) {
        if (expirationDate.length > 4) return
        _uiState.update { it.copy(expirationDate = expirationDate.filter(Char::isDigit)) }
        if (expirationDate.length == 4) {
            viewModelScope.launch { _commandFocus.emit(FocusDirection.Next) }
        }
    }


    fun setOwnerName(ownerName: String) = _uiState.update { it.copy(ownerName = ownerName) }

    fun setPassword(password: String) {
        if (password.length > 4) return
        _uiState.update { it.copy(password = password.filter(Char::isDigit)) }
        if (password.length == 4) {
            viewModelScope.launch { _commandFocus.emit(FocusDirection.Next) }
        }
    }

    fun setBank(selectedBank: CardBankInformation) =
        _uiState.update { it.copy(selectedBank = selectedBank) }

    /**
     * 현재 입력된 카드 정보를 저장소에 추가합니다.
     * 카드가 성공적으로 추가되면, cardAdded 상태를 true로 변경합니다.
     *
     * UI에서는 cardAdded가 true로 변경되었을 때, 네비게이션이나 이벤트 처리를 할 수 있습니다.
     */
    fun addCard() {
        val currentUiState = _uiState.value
        runCatching {
            val cardNumbers = currentUiState.cardNumber.chunked(4).map { CardNumber(it) }
            val expiredDate =
                YearMonth.parse(currentUiState.expirationDate, DateTimeFormatter.ofPattern("MMyy"))

            CreditCard(
                cardNumbers = cardNumbers,
                expiredDate = expiredDate,
                password = currentUiState.password,
                ownerName = currentUiState.ownerName,
                bankType = currentUiState.selectedBank.bankType
            )
        }.onSuccess { card ->
            repository.addCard(card)
            viewModelScope.launch { _cardAdded.emit(true) }
        }.onFailure { e ->
            Log.e("AddCard", "Error adding card: ${e.message}")
            viewModelScope.launch { _errorFlow.emit(e) }
        }
    }
}
