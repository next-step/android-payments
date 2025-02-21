package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.ui.CardAddScreen
import nextstep.payments.ui.theme.PaymentsTheme

class CardAddActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                CardAddScreen(
                    onBackPressed = { onBackPressedDispatcher.onBackPressed() }
                )
            }
        }
    }
}
