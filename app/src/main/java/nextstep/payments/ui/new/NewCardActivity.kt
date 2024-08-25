package nextstep.payments.ui.new

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.data.bank.BankRepository
import nextstep.payments.data.bank.impl.BankRepositoryImpl
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity : ComponentActivity() {

    private val bankRepository: BankRepository = BankRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                val viewModel: NewCardViewModel = viewModel()
                NewCardScreen(
                    viewModel = viewModel,
                    bankRepository,
                    navigateToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    },
                )
            }
        }
    }
}
