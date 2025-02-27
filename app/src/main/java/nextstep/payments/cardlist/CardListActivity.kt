package nextstep.payments.cardlist

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import nextstep.payments.base.BaseActivity
import nextstep.payments.newcard.NewCardActivity

class CardListActivity : BaseActivity() {
    private val viewModel by viewModels<CardListViewModel>()

    @Composable
    override fun Screen() {
        val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                viewModel.sendEvent(CardListEvent.OnCreateNewCard)
            }
        }

        CardListScreen(
            viewModel = viewModel,
            navigateToNewCardScreen = { navigateToNewCardScreen(launcher) }
        )
    }

    private fun navigateToNewCardScreen(launcher: ManagedActivityResultLauncher<Intent, ActivityResult>) {
        launcher.launch(NewCardActivity.newIntent(this))
    }
}
