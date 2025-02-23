package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.ui.newcard.NewCardActivity
import nextstep.payments.ui.payments.PaymentsScreen
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                PaymentsScreen(onAddCardClick = ::navigateToNewCard)
            }
        }
    }

    private fun navigateToNewCard() {
        startActivity(NewCardActivity.getIntent(this))
    }
}
