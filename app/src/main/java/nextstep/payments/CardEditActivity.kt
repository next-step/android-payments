package nextstep.payments

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.model.Card
import nextstep.payments.ui.CardEditScreen
import nextstep.payments.ui.NewCardScreen
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.viewmodel.CardEditViewModel
import nextstep.payments.viewmodel.NewCardViewModel
import nextstep.payments.viewmodel.ViewModelFactory

class CardEditActivity : ComponentActivity() {
    private val viewModel: CardEditViewModel by viewModels { ViewModelFactory() }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val card = intent.getParcelableExtra("CARD", Card::class.java)
        card?.let {
            viewModel.readCard(it)
        }

        setContent {
            PaymentsTheme {
                CardEditScreen(
                    viewModel = viewModel,
                    navigateToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    },
                    onBackButtonClicked = {
                        finish()
                    },
                    onSaveButtonClicked = {
                        viewModel.editCard(
                            cardId = card?.id ?: 0,
                            card = Card(
                                id = card?.id ?: 0,
                                cardNumber = viewModel.cardNumber.value,
                                expiredDate = viewModel.expiredDate.value,
                                ownerName = viewModel.ownerName.value,
                                password = viewModel.password.value,
                                color = viewModel.cardCompanyType.value.color,
                                cardCompany = viewModel.cardCompanyType.value.name,
                            )
                        )
                    },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardEditScreenPreview() {
    PaymentsTheme {
        CardEditScreen(
            viewModel = CardEditViewModel().apply {
                setCardNumber("0000 - 0000 - 0000 - 0000")
                setExpiredDate("00 / 00")
                setOwnerName("최고심")
                setPassword("1234")
            },
            navigateToCardList = {},
            onBackButtonClicked = {},
            onSaveButtonClicked = {},
        )
    }
}
