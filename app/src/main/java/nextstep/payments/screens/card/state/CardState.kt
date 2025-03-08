package nextstep.payments.screens.card.state

data class CardState(
    val id: Int,
    val selectedCardCompany: CardCompanyState? = null,
    val cardNumber: String = "",
    val expiredDate: String = "",
    val ownerName: String = "",
    val password: String = "",
) {
    fun isFormValid(): Boolean {
        return cardNumber.isNotBlank() &&
                expiredDate.isNotBlank() &&
                ownerName.isNotBlank() &&
                password.isNotBlank() &&
                selectedCardCompany != null
    }
}
