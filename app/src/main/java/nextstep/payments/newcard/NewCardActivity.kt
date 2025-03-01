package nextstep.payments.newcard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.newcard.screen.NewCardScreen
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PaymentsTheme {
                NewCardScreen(
                    onBack = { finish() }
                )
            }
        }
    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, NewCardActivity::class.java)
        }
    }
}
