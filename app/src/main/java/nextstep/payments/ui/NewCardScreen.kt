package nextstep.payments.ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.ui.component.CardDetailTopBar
import nextstep.payments.ui.component.CardInputField
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.viewmodel.NewCardViewModel

@Composable
internal fun NewCardScreen(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
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
        setCardNumber = viewModel::setCardNumber, // { viewmodel.setCardNumber() } 랑 뭐가 다를까?
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        modifier = modifier
    )
}

@Composable
internal fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    modifier: Modifier = Modifier,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            CardDetailTopBar(
                title = stringResource(R.string.add_card),
                onBackClick = { },
                onSaveClick = { }
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

            CardInputField(
                value = cardNumber,
                onValueChange = setCardNumber,
                label = stringResource(R.string.card_number),
                placeholder = stringResource(R.string.card_number_place_holder),
                modifier = Modifier.fillMaxWidth(),
            )

            CardInputField(
                value = expiredDate,
                onValueChange = setExpiredDate,
                label = stringResource(R.string.expired_date),
                placeholder = stringResource(R.string.expired_date_place_holder),
                modifier = Modifier.fillMaxWidth(),
            )

            CardInputField(
                value = ownerName,
                onValueChange = setOwnerName,
                label = stringResource(R.string.owner_name_label),
                placeholder = stringResource(R.string.owner_name_place_holder),
                modifier = Modifier.fillMaxWidth(),
            )

            CardInputField(
                value = password,
                onValueChange = setPassword,
                label = stringResource(R.string.password),
                placeholder = stringResource(R.string.password_place_holder),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatefulNewCardScreenPreview() {
    NewCardScreen(
        viewModel = NewCardViewModel().apply {
            setCardNumber("0000 - 0000 - 0000 - 0000")
            setExpiredDate("00 / 00")
            setOwnerName("홍길동")
            setPassword("0000")
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun StatelessNewCardScreenPreview() {
    NewCardScreen(
        cardNumber = "0000 - 0000 - 0000 - 0000",
        expiredDate = "00 / 00",
        ownerName = "홍길동",
        password = "0000",
        setCardNumber = { },
        setExpiredDate = { },
        setOwnerName = { },
        setPassword = { },
    )
}
