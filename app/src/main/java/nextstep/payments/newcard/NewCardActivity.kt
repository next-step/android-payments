package nextstep.payments.newcard

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import nextstep.payments.base.BaseActivity

class NewCardActivity() : BaseActivity() {
    private val viewModel by viewModels<NewCardViewModel>()

    @Composable
    override fun Screen() {
        NewCardScreen(viewModel = viewModel)
    }
}
