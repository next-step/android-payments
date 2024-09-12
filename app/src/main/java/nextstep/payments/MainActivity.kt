package nextstep.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import nextstep.payments.screen.cardlist.CardListScreen
import nextstep.payments.screen.cardlist.CardListViewModel
import nextstep.payments.screen.cardmanage.ManageCardActivity
import nextstep.payments.screen.model.arg.CardArgType
import nextstep.payments.screen.model.toModel
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {
    private val viewModel : CardListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                if (it.resultCode == RESULT_OK) {
                    viewModel.fetchCardList()
                }
            }
            PaymentsTheme {
                CardListScreen(
                    viewModel = viewModel,
                    navigateToAddCard = {
                        launcher.launch(
                            Intent(this, ManageCardActivity::class.java)
                        )
                    },
                    navigateToEditCard = { card ->
                        launcher.launch(
                            Intent(this, ManageCardActivity::class.java).apply {
                                putExtra(
                                    CardArgType.MANAGE_CARD_TYPE_ARG,
                                    CardArgType.EditCardArg(card.toModel())
                                )
                            }
                        )
                    }
                )
            }
        }
    }
}
