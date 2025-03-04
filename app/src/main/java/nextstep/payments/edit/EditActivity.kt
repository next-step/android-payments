package nextstep.payments.edit

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import nextstep.payments.base.BaseActivity

class EditActivity : BaseActivity() {
    private val viewModel by viewModels<EditViewModel> {
        val cardId = intent.getIntExtra(CARD_ID, -1)
        EditViewModel.getFactory(cardId)
    }

    @Composable
    override fun Screen() {
        EditScreen(
            popBackStack = {},
            popBackStackWithResult = {},
            viewModel = viewModel,
        )
    }

    companion object {
        private const val CARD_ID = "cardId"

        fun newIntent(context: Context, cardId: Int): Intent {
            return Intent(context, EditActivity::class.java).apply {
                putExtra(CARD_ID, cardId)
            }
        }
    }
}
