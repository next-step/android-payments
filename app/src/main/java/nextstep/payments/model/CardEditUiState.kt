package nextstep.payments.model

data class CardEditUiState(
    val cardId: Int,
    val cardColor: Long,
    val cardCompany: String,
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val cardEdited: Boolean,
    val cardCompanyType: CardCompanyType,
    val initialCardNumber: String,
    val initialExpiredDate: String,
    val initialOwnerName: String,
    val initialPassword: String,
    val currentCard: Card
) {
    val isCardEdited: Boolean
        get() = cardNumber != initialCardNumber ||
                expiredDate != initialExpiredDate ||
                ownerName != initialOwnerName ||
                password != initialPassword
}
