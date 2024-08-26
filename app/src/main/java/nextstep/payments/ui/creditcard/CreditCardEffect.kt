package nextstep.payments.ui.creditcard

sealed interface CreditCardEffect {
    data class NavigateToRegisterCard(
        val cardId: String?,
    ) : CreditCardEffect
}
