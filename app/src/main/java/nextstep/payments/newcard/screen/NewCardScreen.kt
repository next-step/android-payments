package nextstep.payments.newcard.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.newcard.NewCardViewModel
import nextstep.payments.newcard.component.NewCardTopBar
import nextstep.payments.common.component.PaymentCard
import nextstep.payments.newcard.component.CardNumberTextField
import nextstep.payments.newcard.component.ExpiredDateTextField
import nextstep.payments.newcard.component.OwnerNameTextField
import nextstep.payments.newcard.component.PasswordTextField

@Composable
fun NewCardScreen(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
    onBack: () -> Unit,
) {
    val cardNumber by viewModel.cardNumber.collectAsStateWithLifecycle()
    val expiredDate by viewModel.expiredDate.collectAsStateWithLifecycle()
    val ownerName by viewModel.ownerName.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    NewCardScreen(
        cardNumber = cardNumber,
        expiredDate = expiredDate,
        ownerName = ownerName,
        password = password,
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        onBack = onBack,
        onSave = {
            viewModel.addCard()
            onBack()
        },
        modifier = modifier
    )

}

@Composable
fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBack,
                onSaveClick = onSave
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentCard()

            Spacer(modifier = Modifier.height(10.dp))

            CardNumberTextField(
                modifier = Modifier.fillMaxWidth(),
                cardNumber = cardNumber,
                setCardNumber = setCardNumber
            )

            ExpiredDateTextField(
                modifier = Modifier.fillMaxWidth(),
                expiredDate = expiredDate,
                setExpiredDate = setExpiredDate
            )

            OwnerNameTextField(
                modifier = Modifier.fillMaxWidth(),
                ownerName = ownerName,
                setOwnerName = setOwnerName
            )

            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                password = password,
                setPassword = setPassword
            )
        }
    }
}


@Preview
@Composable
private fun StatefulNewCardScreenPreview() {
    NewCardScreen(
        viewModel = NewCardViewModel().apply {
            setCardNumber("0000 - 0000 - 0000 - 0000")
            setExpiredDate("00 / 00")
            setOwnerName("홍길동")
            setPassword("0000")
        },
        onBack = {}
    )
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    NewCardScreen(
        cardNumber = "0000 - 0000 - 0000 - 0000",
        expiredDate = "00 / 00",
        ownerName = "홍길동",
        password = "0000",
        setCardNumber = {},
        setExpiredDate = {},
        setOwnerName = {},
        setPassword = {},
        onSave = {},
        onBack = {}
    )
}
