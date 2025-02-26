package nextstep.payments

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import nextstep.payments.base.BaseActivity
import nextstep.payments.newcard.NewCardScreen
import nextstep.payments.newcard.NewCardViewModel

class MainActivity() : BaseActivity() {
    private val viewModel by viewModels<NewCardViewModel>()

    @Composable
    override fun Screen() {
        NewCardScreen(viewModel = viewModel)
    }
}
