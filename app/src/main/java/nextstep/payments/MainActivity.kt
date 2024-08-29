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
import nextstep.payments.ui.card.CardListScreen
import nextstep.payments.ui.card.CardViewModel
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {

    private val viewModel: CardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                        if (it.resultCode == RESULT_OK) {
                            viewModel.fetchCards()
                        }
                    }
                    CardListScreen(
                        viewModel = viewModel,
                        onAddPaymentCard = {
                            val intent = Intent(this, NewCardActivity::class.java)
                            launcher.launch(intent)
                        },
                        onEditPaymentCard = { card ->
                            val intent = Intent(this, EditCardActivity::class.java)
                            intent.putExtra("card", card)
                            launcher.launch(intent)
                        }
                    )
                }
            }
        }
    }
}
