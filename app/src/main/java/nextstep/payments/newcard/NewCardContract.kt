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
    data class OnCardNumberChanged(val cardNumber: String): NewCardEvent()
    data class OnExpiredDateChanged(val expiredDate: String): NewCardEvent()
    data class OnOwnerNameChanged(val ownerName: String): NewCardEvent()
    data class OnPasswordChanged(val password: String): NewCardEvent()
    data object OnClickBackButton: NewCardEvent()
    data object OnClickCompleteButton: NewCardEvent()
}

sealed class NewCardSideEffect: ScreenSideEffect {
    data object NavigateBack: NewCardSideEffect()
    data object NavigateBackWithNeedReload: NewCardSideEffect()
    data object HideBankSelectBottomSheet: NewCardSideEffect()
}
