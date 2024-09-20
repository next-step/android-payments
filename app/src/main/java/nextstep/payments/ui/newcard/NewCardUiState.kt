package nextstep.payments.ui.newcard

import nextstep.payments.ui.component.card.CardBankInformation

internal data class NewCardUiState(
    val cardNumber: String,
    val expirationDate: String,
    val ownerName: String,
    val password: String,
    val selectedBank: CardBankInformation,
) {
    val isShowBanks get() = selectedBank == CardBankInformation.None

    companion object {
        val default = NewCardUiState(
            cardNumber = "",
            expirationDate = "",
            ownerName = "",
            password = "",
            selectedBank = CardBankInformation.None
        )
    }
}