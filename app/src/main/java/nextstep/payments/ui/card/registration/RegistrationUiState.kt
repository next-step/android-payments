package nextstep.payments.ui.card.registration

sealed interface RegistrationUiState {
    data object NewCard : RegistrationUiState
    data object EditCard : RegistrationUiState
}
