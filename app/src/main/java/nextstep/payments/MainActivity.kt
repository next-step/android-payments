package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import nextstep.payments.navigate.NavigateScreen
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {

    private val newCardViewModel: NewCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PaymentsTheme {
                NavigateScreen(newCardViewModel)
            }
        }
    }
}