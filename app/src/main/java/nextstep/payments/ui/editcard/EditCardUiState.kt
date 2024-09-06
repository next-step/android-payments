package nextstep.payments.ui.editcard

import nextstep.payments.ui.component.card.CardBankInformation

data class EditCardUiState(
    val cardNumber: String,
    val expirationDate: String,
    val ownerName: String,
    val password: String,
    val bank: CardBankInformation,
)
