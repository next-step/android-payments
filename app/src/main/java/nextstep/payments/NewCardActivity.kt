package nextstep.payments

import CardCompanySelectBottomSheet
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.model.Card
import nextstep.payments.ui.NewCardScreen
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.viewmodel.NewCardViewModel
import nextstep.payments.viewmodel.ViewModelFactory

class NewCardActivity : ComponentActivity() {
    private val viewModel: NewCardViewModel by viewModels { ViewModelFactory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                NewCardScreen(
                    viewModel = viewModel,
                    navigateToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    },
                    onBackButtonClicked = {
                        finish()
                    },
                    onSaveButtonClicked = {
                        viewModel.addCard(
                            Card(
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

                CardCompanySelectBottomSheet(
                    viewModel = viewModel,
                    onCompanySelected = { company ->
                        viewModel.updateCardCompany(company)
                    },
                    onDismissRequest = { }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            viewModel = NewCardViewModel().apply {
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
