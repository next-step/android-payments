package nextstep.payments.cardlist

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import nextstep.payments.base.BaseActivity

class CardListActivity : BaseActivity() {
    private val viewModel by viewModels<CardListViewModel>()

    @Composable
    override fun Screen() {
        CardListScreen(viewModel = viewModel)
    }
}
