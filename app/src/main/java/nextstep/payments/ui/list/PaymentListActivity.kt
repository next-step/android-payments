package nextstep.payments.ui.list

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.activity.viewModels
import nextstep.payments.ui.register.PaymentRegisterActivity
import nextstep.payments.ui.theme.PaymentsTheme

internal class PaymentListActivity : ComponentActivity() {

    private val viewModel: PaymentListViewModel by viewModels(
        factoryProducer = { PaymentListViewModel.Factory() }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val launcher = rememberLauncherForActivityResult(StartActivityForResult()) {
                if (it.resultCode == Activity.RESULT_OK) {
                    viewModel.refresh()
                }
            }

            PaymentsTheme {
                PaymentListScreen(
                    viewModel = viewModel,
                    onAddCardClick = {
                        val intent = Intent(this, PaymentRegisterActivity::class.java)
                        launcher.launch(intent)
                    }
                )
            }
        }
    }
}
