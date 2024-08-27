package nextstep.payments.ui.new

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.data.Card
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
                val card = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    intent.getParcelableExtra("card", Card::class.java)
                } else {
                    intent.getParcelableExtra("card")
                }
                if (card != null) {
                    viewModel.setCardNumber(card.cardNumber)
                    viewModel.setCardCompany(card.cardCompany)
                    viewModel.setCardColor(Color(card.cardColor))
                    viewModel.setPassword(card.password)
                    viewModel.setOwnerName(card.ownerName)
                    viewModel.setExpiredDate(card.expiredDate)
                    viewModel.setBankType(card.bankType)
                }

                NewCardScreen(
                    viewModel = viewModel,
                    bankRepository,
                    navigateToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    },
                    card = card
                )
            }
        }
    }
}
