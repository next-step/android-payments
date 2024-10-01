package nextstep.payments.model

sealed interface CardUiState {
    data object Empty : CardUiState
    data object One : CardUiState
    data object Many : CardUiState
}
