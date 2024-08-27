package nextstep.payments.ui.screen.editcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.screen.newcard.model.BankTypeModel
import nextstep.payments.ui.screen.newcard.model.toUiModel

class EditCardViewModel : ViewModel() {

    private val _state = MutableStateFlow<EditCardState>(EditCardState())
    val state = _state.asStateFlow()

    fun handleEvent(event: EditCardEvent) {
        when (event) {
            is EditCardEvent.OnInit -> {
                val card = PaymentCardsRepository.getCard(event.cardId)
                val bankList = BankTypeModel.entries
                if (card != null) {
                    _state.value = _state.value.copy(
                        cardNumber = card.cardNumber,
                        expiredDate = card.cardExpiredDate,
                        ownerName = card.cardOwnerName,
                        password = card.cardPassword,
                        bankType = card.bankType.toUiModel(),
                        cardBrands = bankList,
                    )
                } else {
                    // 에러 분기 처리 필요
                }
            }

            is EditCardEvent.OnBankTypeChanged -> {
                _state.value = _state.value.copy(
                    bankType = event.bankType
                )
            }

            is EditCardEvent.OnCardNumberChanged -> {
                _state.value = _state.value.copy(
                    cardNumber = event.cardNumber
                )
            }

            is EditCardEvent.OnExpiredDateChanged -> {
                _state.value = _state.value.copy(
                    expiredDate = event.expiredDate
                )
            }

            is EditCardEvent.OnOwnerNameChanged -> {
                _state.value = _state.value.copy(
                    ownerName = event.ownerName
                )
            }

            is EditCardEvent.OnPasswordChanged -> {
                _state.value = _state.value.copy(
                    password = event.password
                )
            }

            is EditCardEvent.OnCardClicked -> {
                _state.value = _state.value.copy(
                    showChangeBankType = true
                )
            }

            is EditCardEvent.OnDismissChangeBackType -> {
                _state.value = _state.value.copy(
                    showChangeBankType = false
                )
            }

            is EditCardEvent.OnBackClicked -> {
                _state.value = _state.value.copy(
                    backPressed = true
                )
            }

            is EditCardEvent.OnSaveClicked -> {
                _state.value = _state.value.copy(
                    saved = true
                )
            }
        }
    }
}
