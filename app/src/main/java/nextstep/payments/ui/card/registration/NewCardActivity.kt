package nextstep.payments.ui.card.registration

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import nextstep.payments.data.Card
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity : ComponentActivity() {

    private val viewModel: NewCardViewModel by viewModels { NewCardViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
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

    override fun onResume() {
        super.onResume()
        viewModel.setData()
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveData()
    }
}


