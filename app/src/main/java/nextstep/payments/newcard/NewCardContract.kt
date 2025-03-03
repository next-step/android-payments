package nextstep.payments.newcard

import nextstep.payments.base.ScreenEvent
import nextstep.payments.base.ScreenSideEffect
import nextstep.payments.base.ScreenState
import nextstep.payments.model.BankType

data class NewCardState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val bankType: BankType = BankType.NOT_SELECTED,
): ScreenState

sealed class NewCardEvent: ScreenEvent {
    data class OnCardNumberChange(val cardNumber: String): NewCardEvent()
    data class OnExpiredDateChange(val expiredDate: String): NewCardEvent()
    data class OnOwnerNameChange(val ownerName: String): NewCardEvent()
    data class OnPasswordChange(val password: String): NewCardEvent()
    data object OnClickBackButton: NewCardEvent()
    data object OnClickCompleteButton: NewCardEvent()
    data class OnClickBankSelectButton(val bankType: BankType): NewCardEvent()
    data object OnDismissBottomSheet: NewCardEvent()
}

sealed class NewCardSideEffect: ScreenSideEffect {
    data object NavigateBack: NewCardSideEffect()
    data object NavigateBackWithNeedReload: NewCardSideEffect()
    data object HideBottomSheet: NewCardSideEffect()
}
