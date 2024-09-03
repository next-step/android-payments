package nextstep.payments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import nextstep.payments.data.model.Card
import nextstep.payments.ui.editcard.EditCardScreen
import nextstep.payments.ui.editcard.EditCardViewModel
import nextstep.payments.ui.newcard.model.BankUI
import nextstep.payments.ui.theme.PaymentsTheme

class EditCardActivity : ComponentActivity() {
    private val viewModel: EditCardViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaymentsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val card = intent.getParcelableExtra("card", Card::class.java)!!
                    viewModel.apply {
                        setId(card.id)
                        setCardNumber(card.cardNumber)
                        setExpiredDate(card.expiredDate)
                        setOwnerName(card.ownerName)
                        setPassword(card.password)
                        setBank(BankUI.fromBank(card.bank))
                    }
                    EditCardScreen(
                        viewModel = viewModel,
                        navigateToCardList = {
                            setResult(RESULT_OK)
                            finish()
                        },
                        onBackClick = {
                            finish()
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
