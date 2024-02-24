package nextstep.payments.ui.register

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import nextstep.payments.ui.theme.PaymentsTheme

class PaymentRegisterActivity : ComponentActivity() {

    private val viewModel: PaymentRegisterViewModel by viewModels(
        factoryProducer = {
            PaymentRegisterViewModel.Factory()
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lifecycleScope.launch {
            viewModel.isRegistered.collect { registered ->
                if (registered) {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        }

        setContent {
            PaymentsTheme {
                PaymentRegisterScreen(
                    viewModel = viewModel,
                    onBackClick = { finish() },
                )
            }
        }
    }
}
