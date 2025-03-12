package nextstep.payments.ui.newcard

sealed interface NewCardEffect {
    data class ShowError(val message: String) : NewCardEffect
    data object CardAdded : NewCardEffect
}
