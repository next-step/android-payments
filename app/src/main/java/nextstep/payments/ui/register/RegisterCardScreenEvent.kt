package nextstep.payments.ui.register

sealed interface RegisterCardScreenEvent {
    data class OnCardNumberChanged(
        val cardNumber: String,
    ) : RegisterCardScreenEvent

    data class OnExpiredDateChanged(
        val expiredDate: String,
    ) : RegisterCardScreenEvent

    data class OnOwnerNameChanged(
        val ownerName: String,
    ) : RegisterCardScreenEvent

    data class OnPasswordChanged(
        val password: String,
    ) : RegisterCardScreenEvent

    data object OnRegisterCardClicked : RegisterCardScreenEvent
}
