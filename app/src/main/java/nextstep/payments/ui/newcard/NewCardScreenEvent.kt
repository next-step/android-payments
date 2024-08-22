package nextstep.payments.ui.newcard

sealed interface NewCardScreenEvent {
    data class OnCardNumberChanged(
        val cardNumber: String,
    ) : NewCardScreenEvent

    data class OnExpiredDateChanged(
        val expiredDate: String,
    ) : NewCardScreenEvent

    data class OnOwnerNameChanged(
        val ownerName: String,
    ) : NewCardScreenEvent

    data class OnPasswordChanged(
        val password: String,
    ) : NewCardScreenEvent

    data object OnRegisterCardClicked : NewCardScreenEvent
}
