package nextstep.payments.newcard.model

sealed class NewCardEvent {
    data class ShowToast(val resId: Int) : NewCardEvent()
}
