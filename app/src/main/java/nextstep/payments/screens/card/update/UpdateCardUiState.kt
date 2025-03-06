package nextstep.payments.screens.card.update

import nextstep.payments.screens.card.state.CardState

sealed interface UpdateCardUiState {
    val cardState: CardState
    val cardUpdated: Boolean

    data class EditCardUiState(
        val cardForEdit: CardState,
        override val cardState: CardState = cardForEdit.copy(),
        override val cardUpdated: Boolean = false,
    ) : UpdateCardUiState {
        fun isFormValid(): Boolean {
            return cardState.isFormValid() &&
                    cardState != cardForEdit
        }
    }

    data class AddCardUiState(
        override val cardState: CardState = CardState(),
        override val cardUpdated: Boolean = false,
    ) : UpdateCardUiState
}
