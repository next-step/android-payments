package nextstep.payments.screens.card.update

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.screens.card.state.CardState
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.util.parcelable

class UpdateCardActivity : ComponentActivity() {
    private val cardState: CardState? by lazy { intent.parcelable<CardState>(CARD_KEY) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                UpdateCardScreen(
                    cardState = cardState,
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

        fun getIntentForPutExtraCardState(context: Context, cardState: CardState): Intent {
            return Intent(context, UpdateCardActivity::class.java).apply {
                putExtra(CARD_KEY, cardState)
            }
        }
    }
}
