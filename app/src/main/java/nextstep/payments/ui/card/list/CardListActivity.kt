package nextstep.payments.ui.card.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.card.newcard.NewCardActivity
import nextstep.payments.ui.theme.PaymentsTheme

class CardListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                val viewModel: CardListViewModel = viewModel()
                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                        if (it.resultCode == RESULT_OK) {
                            viewModel.fetchCards()
                        }
                    }

                CardListScreen(
                    viewModel = viewModel,
                    onShowNewCard = {
                        launcher.launch(Intent(this, NewCardActivity::class.java))
                    },
                )
            }
        }
    }

}
