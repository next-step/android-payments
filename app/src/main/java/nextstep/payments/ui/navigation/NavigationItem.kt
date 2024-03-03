package nextstep.payments.ui.navigation

sealed class NavigationItem(val route: String) {

    data object PaymentCards : NavigationItem("PaymentCards")

    data object AddCard : NavigationItem("AddCard")

}
