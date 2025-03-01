package nextstep.payments.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.newcard.NewCardActivity
import nextstep.payments.list.screen.CardListScreen
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                val context = LocalContext.current
                val viewModel: CardListViewModel = viewModel()
                val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it.resultCode == RESULT_OK) {
                        viewModel.fetchCards()
                    }
                }

                CardListScreen(
                    viewModel = viewModel,
                    moveToAddCard = {
                        launcher.launch(NewCardActivity.intent(context = context))
                    }
                )
            }
        }
    }
}
