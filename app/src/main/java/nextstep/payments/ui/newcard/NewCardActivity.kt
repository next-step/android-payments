package nextstep.payments.ui.newcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity : ComponentActivity() {
    private val viewModel : NewCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                NewCardScreen(
                    viewModel = viewModel,
                    navigateToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    }
                )
            }
        }
    }
}
