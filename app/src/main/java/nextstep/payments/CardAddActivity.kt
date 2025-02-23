package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.add.CardAddScreen
import nextstep.payments.ui.add.CardAddViewModel
import nextstep.payments.ui.theme.PaymentsTheme

class CardAddActivity : ComponentActivity() {
    private val cardAddViewModel: CardAddViewModel by viewModels {
        CardAddViewModel.getFactory(PaymentCardsRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaymentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CardAddScreen(
                        cardAddViewModel = cardAddViewModel,
                        onBackClick = { finish() },
                        onSaveCard = {
                            setResult(RESULT_OK)
                            finish()
                        }
                    )
                }
            }
        }
    }
}
