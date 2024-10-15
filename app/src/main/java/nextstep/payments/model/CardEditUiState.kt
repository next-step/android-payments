package nextstep.payments.model

data class CardEditUiState(
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
    val cardEdited: Boolean = false,
    val cardCompanyType: CardCompanyType = CardCompanyType.None,
    val initialCardNumber: String = "",
    val initialExpiredDate: String = "",
    val initialOwnerName: String = "",
    val initialPassword: String = "",
    var currentCard: Card = Card(
        id = 0,
        cardNumber = "",
        expiredDate = "",
        ownerName = "",
        password = "",
        color = 0L,
        cardCompany = ""
    ),
    val isCardEdited: Boolean = false
)
