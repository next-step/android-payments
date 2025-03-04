package nextstep.payments.edit

import nextstep.payments.base.ScreenEvent
import nextstep.payments.base.ScreenSideEffect
import nextstep.payments.base.ScreenState
import nextstep.payments.model.BankType
import nextstep.payments.model.Card

data class EditState(
    val id: Int,
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val bankType: BankType = BankType.NOT_SELECTED,
): ScreenState {
    val card: Card
        get() = Card(
            id = id,
            cardNumber = cardNumber,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password,
            bankType = bankType
        )
}

sealed class EditEvent: ScreenEvent {
    data class OnCardNumberChange(val cardNumber: String): EditEvent()
    data class OnExpiredDateChange(val expiredDate: String): EditEvent()
    data class OnOwnerNameChange(val ownerName: String): EditEvent()
    data class OnPasswordChange(val password: String): EditEvent()
    data object OnClickBackButton: EditEvent()
    data object OnClickCompleteButton: EditEvent()
}

sealed class EditSideEffect: ScreenSideEffect {
    data object NavigateBack: EditSideEffect()
    data object NavigateBackWithNeedReload: EditSideEffect()
}