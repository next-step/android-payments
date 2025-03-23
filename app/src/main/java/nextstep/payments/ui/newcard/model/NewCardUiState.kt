package nextstep.payments.ui.newcard.model

data class NewCardUiState(
    val cardAdded: Boolean = false,
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val selectedCard: CardCompany = CardCompany(0, "", 0),
    val showSelectCardBottomSheet: Boolean = false,
    val inputInValidMessage: String = ""
)
