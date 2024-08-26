package nextstep.payments.ui.register

import nextstep.payments.model.CardRegisterResult

sealed interface RegisterCardScreenEffect {
    data class NavigateToCardListScreen(
        val result: CardRegisterResult,
    ) : RegisterCardScreenEffect
}
