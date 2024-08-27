package nextstep.payments.ui.screen.editcard

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import nextstep.payments.data.PaymentCardsRepository
import nextstep.payments.ui.screen.creditcard.model.CardModel
import nextstep.payments.ui.screen.creditcard.model.toData
import nextstep.payments.ui.screen.creditcard.model.toUiModel
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
                    updateState {
                        copy(
                            cardId = card.id,
                            cardNumber = card.cardNumber,
                            expiredDate = card.cardExpiredDate,
                            ownerName = card.cardOwnerName,
                            password = card.cardPassword,
                            bankType = card.bankType?.toUiModel(),
                            cardBrands = bankList,
                        )
                    }
                } else {
                    updateState {
                        copy(
                            backPressed = true
                        )
                    }
                }
            }

            is EditCardEvent.OnBankTypeChanged -> {
                updateState {
                    copy(
                        bankType = event.bankType
                    )
                }
            }

            is EditCardEvent.OnCardNumberChanged -> {
                updateState {
                    copy(
                        cardNumber = event.cardNumber
                    )
                }
            }

            is EditCardEvent.OnExpiredDateChanged -> {
                updateState {
                    copy(
                        expiredDate = event.expiredDate
                    )
                }
            }

            is EditCardEvent.OnOwnerNameChanged -> {
                updateState {
                    copy(
                        ownerName = event.ownerName
                    )
                }
            }

            is EditCardEvent.OnPasswordChanged -> {
                updateState {
                    copy(
                        password = event.password
                    )
                }
            }

            is EditCardEvent.OnCardClicked -> {
                updateState {
                    copy(
                        showChangeBankType = true
                    )
                }
            }

            is EditCardEvent.OnDismissChangeBackType -> {
                updateState {
                    copy(
                        showChangeBankType = false
                    )
                }
            }

            is EditCardEvent.OnBackClicked -> {
                updateState {
                    copy(
                        backPressed = true
                    )
                }
            }

            is EditCardEvent.OnDismissSnackbar -> {
                updateState {
                    copy(
                        message = null
                    )
                }
            }

            is EditCardEvent.OnSaveClicked -> {
                saveCard()
            }
        }
    }

    private fun saveCard() {
        val originalCard = PaymentCardsRepository.getCard(state.value.cardId)?.toUiModel()
        val newCard = CardModel(
            state.value.cardId,
            state.value.cardNumber,
            state.value.ownerName,
            state.value.expiredDate,
            state.value.password,
            state.value.bankType
        )

        if (originalCard != newCard) {
            PaymentCardsRepository.updateCard(newCard.toData())
            updateState { copy(saved = true) }
        } else {
            updateState { copy(message = "변경사항이 없습니다.") }
        }
    }

    private fun updateState(newState: EditCardState.() -> EditCardState) {
        _state.update(newState)
    }
}
