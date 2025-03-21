package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import nextstep.payments.ui.newcard.NewCardScreen
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity: ComponentActivity() {
    private val viewModel: NewCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PaymentsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
}