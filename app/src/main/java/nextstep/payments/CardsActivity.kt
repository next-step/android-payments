package nextstep.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.CardsScreen
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.viewmodel.CardsViewModel
import nextstep.payments.viewmodel.ViewModelFactory

class CardsActivity : ComponentActivity() {
    private val viewModel: CardsViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                val cards by viewModel.cards.collectAsState()
                val cardAdded by viewModel.cardAdded.collectAsState()
                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                        if (it.resultCode == RESULT_OK) {
                            viewModel.fetchCards()
                            viewModel.notifyCardAdded()
                        }
                    }
                CardsScreen(
                    onCardAddClicked = {
                        val intent = Intent(this, NewCardActivity::class.java)
                        launcher.launch(intent)
                    },
                    cards = cards,
                    cardAdded = cardAdded
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardsScreenPreview() {
    PaymentsTheme {
        CardsScreen(onCardAddClicked = {}, cards = emptyList(), cardAdded = false)
    }
}
