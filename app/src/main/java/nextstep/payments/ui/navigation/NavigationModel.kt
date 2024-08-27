package nextstep.payments.ui.navigation

sealed class NavigationModel(val route: String) {
    data object AddPaymentCard : NavigationModel("AddCard")
    data object PaymentCards : NavigationModel("PaymentCards")
}
