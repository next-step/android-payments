package nextstep.payments.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.new.NewCardActivity
import nextstep.payments.ui.new.NewCardViewModel
import nextstep.payments.ui.theme.PaymentsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                val viewModel : NewCardViewModel = viewModel()
                val cardUiState by viewModel.cardUiState.collectAsState()

                val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it.resultCode == RESULT_OK) {
                        viewModel.fetchCards()
                    }
                }
                PaymentMain(cardUiState = cardUiState) {
                    val intent = Intent(this, NewCardActivity::class.java)
                    launcher.launch(intent)
                }
            }
        }
    }
}


