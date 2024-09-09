package nextstep.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.CardsScreenStateful
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.viewmodel.CardsViewModel
import nextstep.payments.viewmodel.ViewModelFactory

class CardsActivity : ComponentActivity() {
    private val viewModel: CardsViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                val launcher =
                    rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                        if (it.resultCode == RESULT_OK) {
                            viewModel.fetchCards()
                            viewModel.notifyCardAdded()
                        }
                    }
                CardsScreenStateful(
                    onCardAddClicked = {
                        val intent = Intent(this, NewCardActivity::class.java)
                        launcher.launch(intent)
                    },
                    viewModel = viewModel
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardsScreenPreview() {
    PaymentsTheme {
        CardsScreenStateful(
            onCardAddClicked = {},
            viewModel = CardsViewModel()
        )
    }
}
