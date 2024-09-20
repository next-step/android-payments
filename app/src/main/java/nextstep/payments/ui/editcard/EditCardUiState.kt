package nextstep.payments.ui.editcard

import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.event.UiEvent

internal data class EditCardUiState(
    val cardNumber: String,
    val expirationDate: String,
    val ownerName: String,
    val password: String,
    val bank: CardBankInformation,

    // 업데이트 되었음
    val isUpdated: UiEvent<Unit>? = null,
    // 에러가 발생함
    val isError: UiEvent<Throwable>? = null,
)
