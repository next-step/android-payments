package nextstep.payments.ui.newcard

sealed interface NewCardScreenEffect {
    data class NavigateToCardListScreen(
        val shouldFetchCards: Boolean,
    ) : NewCardScreenEffect
}
