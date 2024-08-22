package nextstep.payments.ui.screen.newcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PaymentsTheme {
                NewCardRoute(
                    navigateToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    },
                )
            }
        }
    }
}
