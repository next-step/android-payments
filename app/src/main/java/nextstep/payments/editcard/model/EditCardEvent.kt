package nextstep.payments.editcard.model

sealed class EditCardEvent {
    data class ShowToast(val resId: Int) : EditCardEvent()
    data class Finish(val noticeResId: Int?): EditCardEvent()
}
