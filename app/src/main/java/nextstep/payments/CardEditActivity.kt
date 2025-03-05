package nextstep.payments

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.edit.CardEditScreen
import nextstep.payments.ui.edit.CardEditViewModel
import nextstep.payments.ui.theme.PaymentsTheme

class CardEditActivity : ComponentActivity() {
    private val cardEditViewModel: CardEditViewModel by viewModels {
        CardEditViewModel.getFactory(PaymentCardsRepository)
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
                    CardEditScreen(
                        cardEditViewModel = cardEditViewModel,
                        onBackClick = { finish() },
                        onSaveCard = {
                            setResult(RESULT_OK)
                            finish()
                        },
                        onCardUpdateFailed = {
                            Toast.makeText(
                                this,
                                R.string.card_info_change_request,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val EXTRA_CARD_ID = "extra_card_id"
    }
}
