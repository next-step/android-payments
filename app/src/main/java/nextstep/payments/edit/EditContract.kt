package nextstep.payments.edit

import nextstep.payments.base.ScreenEvent
import nextstep.payments.base.ScreenSideEffect
import nextstep.payments.base.ScreenState
import nextstep.payments.model.BankType

data class EditState(
    val id: Int,
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val bankType: BankType = BankType.NOT_SELECTED,
): ScreenState

sealed class EditEvent: ScreenEvent

sealed class EditSideEffect: ScreenSideEffect