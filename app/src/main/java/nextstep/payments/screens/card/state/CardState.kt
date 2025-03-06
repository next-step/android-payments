package nextstep.payments.screens.card.state

import nextstep.payments.domain.Card
import nextstep.payments.screens.card.mapper.toState

data class CardState(
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

    fun isEqualTo(other: Card): Boolean {
        return cardNumber == other.numbers &&
                expiredDate == other.expiredDate &&
                ownerName == other.ownerName &&
                password == other.password &&
                selectedCardCompany == other.cardCompany.toState()
    }
}
