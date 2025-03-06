package nextstep.payments.screens.card.update

import nextstep.payments.domain.Card
import nextstep.payments.screens.card.CardCompanyState
import nextstep.payments.screens.card.toDomain
import nextstep.payments.screens.card.toState

sealed interface UpdateCardUiState {
    val cardState: CardState
    val cardUpdated: Boolean

    data class EditCardUiState(
        val cardForEdit: Card,
        override val cardState: CardState = CardState(
            selectedCardCompany = cardForEdit.cardCompany.toState(),
            cardNumber = cardForEdit.numbers,
            expiredDate = cardForEdit.expiredDate,
            ownerName = cardForEdit.ownerName,
            password = cardForEdit.password,
        ),
        override val cardUpdated: Boolean = false,
    ) : UpdateCardUiState {
        fun isFormValid(): Boolean {
            return cardState.cardNumber.isNotBlank() &&
                    cardState.expiredDate.isNotBlank() &&
                    cardState.ownerName.isNotBlank() &&
                    cardState.password.isNotBlank() &&
                    cardState.selectedCardCompany != null &&
                    cardState.isEqualTo(cardForEdit).not()
        }
    }

    data class AddCardUiState(
        override val cardState: CardState = CardState(),
        override val cardUpdated: Boolean = false,
    ) : UpdateCardUiState
}

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

    fun toDomain(): Card? {
        return Card(
            cardCompany = selectedCardCompany?.toDomain() ?: return null,
            numbers = cardNumber,
            expiredDate = expiredDate,
            ownerName = ownerName,
            password = password,
        )
    }
}
