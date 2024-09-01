package nextstep.payments.screen.newcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                NewCardScreen(
                    navigateToCardList = { isAdded ->
                        if(isAdded == NewCardEvent.Success) setResult(RESULT_OK)
                        finish()
                    }
                )
            }
        }
    }
}