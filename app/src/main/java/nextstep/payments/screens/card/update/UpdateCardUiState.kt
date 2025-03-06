package nextstep.payments.screens.card.update

import nextstep.payments.domain.Card
import nextstep.payments.screens.card.state.CardCompanyState
import nextstep.payments.screens.card.mapper.toState
import nextstep.payments.screens.card.state.CardState

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
