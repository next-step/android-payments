package nextstep.payments.screens.card.new

import nextstep.payments.screens.card.CardCompanyState

data class NewCardUiState(
    val selectedCardCompany: CardCompanyState? = null,
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val cardAdded: Boolean = false,
)
