package nextstep.payments.edit_card

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.repository.PaymentCardsRepository
import nextstep.payments.ui.theme.PaymentsTheme

class EditCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                val cardId = intent.getIntExtra("card_id", -1)
                val repository = PaymentCardsRepository

                val extras = MutableCreationExtras().apply {
                    set(EditCardViewModel.CARD_KEY, repository.getCard(cardId))
                }

                val viewModel: EditCardViewModel = viewModel(
                    factory = EditCardViewModel.Factory,
                    extras = extras
                )

                EditCardScreen(
                    onBackButtonClick = { finish() },
                    navigateToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    },
                    viewModel = viewModel,
                )
            }
        }
    }
}