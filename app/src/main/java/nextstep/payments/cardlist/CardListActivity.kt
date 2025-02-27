package nextstep.payments.cardlist

import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import nextstep.payments.base.BaseActivity
import nextstep.payments.newcard.NewCardActivity

class CardListActivity : BaseActivity() {
    private val viewModel by viewModels<CardListViewModel>()

    @Composable
    override fun Screen() {
        CardListScreen(
            viewModel = viewModel,
            navigateToNewCardScreen = { navigateToNewCardScreen() }
        )
    }

    private fun navigateToNewCardScreen() {
        startActivity(NewCardActivity.newIntent(this))
    }
}
