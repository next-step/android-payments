package nextstep.payments.screens.card.update

import nextstep.payments.screens.card.state.CardCompanyState
import nextstep.payments.screens.card.state.CardState

sealed interface UpdateCardUiState {
    val cardUpdated: Boolean
    val isFormValid: Boolean

    data class EditCardUiState(
        val cardState: CardState,
        override val isFormValid: Boolean,
        override val cardUpdated: Boolean = false,
    ) : UpdateCardUiState

    data class AddCardUiState(
        val selectedCardCompany: CardCompanyState? = null,
        val cardNumber: String = "",
        val expiredDate: String = "",
        val ownerName: String = "",
        val password: String = "",
        override val cardUpdated: Boolean = false,
    ) : UpdateCardUiState {
        override val isFormValid: Boolean
            get() = cardNumber.isNotBlank() &&
                    expiredDate.isNotBlank() &&
                    ownerName.isNotBlank() &&
                    password.isNotBlank() &&
                    selectedCardCompany != null
    }
}
