package nextstep.payments.ui.newcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaymentsTheme {
                NewCardScreen(
                    onBackClick = { finish() },
                    navigateToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    }
                )
            }
        }
    }
}
