package nextstep.payments.editcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.editcard.screen.EditCardScreen
import nextstep.payments.ui.theme.PaymentsTheme

class EditCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PaymentsTheme {
                EditCardScreen(
                    cardId = intent.getIntExtra(CARD_ID, -1),
                    onBack = {
                        finish()
                    }
                )
            }
        }
    }

    companion object {
        private const val CARD_ID = "cardId"
        fun intent(context: Context, cardId: Int): Intent {
            return Intent(context, EditCardActivity::class.java).apply {
                putExtra(CARD_ID, cardId)
            }
        }
    }
}
