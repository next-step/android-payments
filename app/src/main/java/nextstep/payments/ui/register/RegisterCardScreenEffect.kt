package nextstep.payments.ui.register

sealed interface RegisterCardScreenEffect {
    data class NavigateToCardListScreen(
        val shouldFetchCards: Boolean,
    ) : RegisterCardScreenEffect
}
