package nextstep.payments.screens.card.new

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity : ComponentActivity() {
    private val card: Card? by lazy { intent.parcelable<Card>(CARD_KEY) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                NewCardScreen(
                    card = card,
                    onBackClick = { finish() },
                    navigateToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    }
                )
            }
        }
    }

    companion object {
        private const val CARD_KEY = "card"

        fun getIntent(context: Context): Intent {
            return Intent(context, UpdateCardActivity::class.java)
        }

        fun getIntent(context: Context, card: Card): Intent {
            return Intent(context, UpdateCardActivity::class.java).apply {
                putExtra(CARD_KEY, card)
            }
        }
    }
}
