package nextstep.payments

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.base.BaseComponentActivity
import nextstep.payments.ui.cardlist.CardListScreen
import nextstep.payments.ui.cardlist.CardListViewModel
import nextstep.payments.ui.newcard.NewCardActivity

class MainActivity : BaseComponentActivity() {
    private val viewModel: CardListViewModel by viewModels()

    @Composable
    override fun SetContent() {
        val launcher =
            rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    viewModel.fetchCards()
                }
            }
        val uiState by viewModel.cardListUiState.collectAsStateWithLifecycle()
        CardListScreen(
            uiState = uiState,
            onRouteToNewCard = {
                launcher.launch(
                    NewCardActivity.newInstance(context = this)
                )
            },
            onRouteToUpdateCard = {

            }
        )
    }
}
