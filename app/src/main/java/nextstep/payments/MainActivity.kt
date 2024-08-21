package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.screen.NewCardScreen
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                NewCardScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen()
    }
}
