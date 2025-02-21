package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.CardListScreen
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.viewmodel.CardListViewModel

class CardListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                CardListScreen()
            }
        }
    }
}