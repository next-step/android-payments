package nextstep.payments.newcard

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import nextstep.payments.base.BaseActivity

class NewCardActivity() : BaseActivity() {
    private val viewModel by viewModels<NewCardViewModel>()

    @Composable
    override fun Screen() {
        NewCardScreen(
            viewModel = viewModel,
            popBackStack = { finish() },
            popBackStackWithResult = { popBackStackWithResult() },
        )
    }

    private fun popBackStackWithResult()  {
        setResult(RESULT_OK)
        finish()
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, NewCardActivity::class.java)
        }
    }
}
