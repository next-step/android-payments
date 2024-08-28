package nextstep.payments.ui.card.newcard

import androidx.annotation.StringRes

sealed interface NewCardSideEffect {

    data class ShowToast(@StringRes val messageRes: Int) : NewCardSideEffect

}
