package nextstep.payments.screens.card.uistate

data class CardUiState(
    val id: Int,
    val selectedCardCompany: CardCompanyUiState? = null,
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
