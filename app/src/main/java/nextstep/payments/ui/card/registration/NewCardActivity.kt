package nextstep.payments.ui.card.registration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import nextstep.payments.data.Card
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity : ComponentActivity() {

    private var card: Card? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<NewCardViewModel>()
        super.onCreate(savedInstanceState)

        (intent?.getParcelableExtra("card") as Card?)?.let {
            viewModel.setOldCard(it)
            viewModel.setUiState(RegistrationUiState.EditCard)
        }

        setContent {
            PaymentsTheme {
                NewCardScreen(
                    viewModel = viewModel,
                    navigateToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    },
                    onBackClick = {
                        finish()
                    }
                )
            }
        }
    }
}


