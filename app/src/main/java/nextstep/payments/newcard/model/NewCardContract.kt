package nextstep.payments.newcard.model

import nextstep.payments.base.ScreenEvent
import nextstep.payments.base.ScreenSideEffect
import nextstep.payments.base.ScreenState

data class NewCardState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
): ScreenState

sealed class NewCardEvent: ScreenEvent {
    data class OnCardNumberChanged(val cardNumber: String): NewCardEvent()
    data class OnExpiredDateChanged(val expiredDate: String): NewCardEvent()
    data class OnOwnerNameChanged(val ownerName: String): NewCardEvent()
    data class OnPasswordChanged(val password: String): NewCardEvent()
}

sealed class NewCardSideEffect: ScreenSideEffect
