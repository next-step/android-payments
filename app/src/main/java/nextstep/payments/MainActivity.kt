package nextstep.payments

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import nextstep.payments.ui.card.list.CardListScreen
import nextstep.payments.ui.card.list.CardListViewModel
import nextstep.payments.ui.card.registration.NewCardActivity
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {

    private val viewModel: CardListViewModel by viewModels { CardListViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it.resultCode == RESULT_OK) {
                        viewModel.fetchCards()
                    }
                }

            PaymentsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CardListScreen(
                        viewModel = viewModel,
                        onAddCard = {
                            val intent = Intent(this, NewCardActivity::class.java)
                            launcher.launch(intent)
                        },
                        onCardClick = { card ->
                            val intent = Intent(this, NewCardActivity::class.java).apply {
                                putExtra("card", card)
                            }
                            launcher.launch(intent)
                        }
                    )
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        viewModel.saveCard()
    }

    override fun onResume() {
        super.onResume()
        viewModel.openCard()
    }
}


