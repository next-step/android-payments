package nextstep.payments.ui.screen.editcard

import nextstep.payments.ui.screen.newcard.model.BankTypeModel

data class EditCardState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val bankType: BankTypeModel? = null,
    val message: String? = null,
)

sealed interface EditCardEvent {
    data class OnCardNumberChanged(val cardNumber: String) : EditCardEvent
    data class OnExpiredDateChanged(val expiredDate: String) : EditCardEvent
    data class OnOwnerNameChanged(val ownerName: String) : EditCardEvent
    data class OnPasswordChanged(val password: String) : EditCardEvent
    data class OnBankTypeChanged(val bankType: BankTypeModel) : EditCardEvent
    data class OnInit(val cardId: String) : EditCardEvent
    data object OnBackClicked : EditCardEvent
    data object OnSaveClicked : EditCardEvent
}
