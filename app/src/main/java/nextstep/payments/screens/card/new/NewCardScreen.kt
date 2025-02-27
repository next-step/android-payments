package nextstep.payments.screens.card.new

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import nextstep.payments.screens.card.new.components.NewCardTopBar
import nextstep.payments.components.PaymentCard
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewCardScreen(
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
        setCardNumber = viewModel::setCardNumber,
        setExpiredDate = viewModel::setExpiredDate,
        setOwnerName = viewModel::setOwnerName,
        setPassword = viewModel::setPassword,
        modifier = modifier,
    )
}


@Composable
fun NewCardScreen(
    cardNumber : String,
    expiredDate : String,
    ownerName : String,
    password: String,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { NewCardTopBar(onBackClick = { TODO() }, onSaveClick = { TODO() }) },
        modifier = modifier
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            
            PaymentCard()
            
            Spacer(modifier = Modifier.height(40.dp))

            CardInformationInputFields(
                cardNumber = cardNumber,
                setCardNumber = setCardNumber,
                expiredDate = expiredDate,
                setExpiredDate = setExpiredDate,
                ownerName = ownerName,
                setOwnerName = setOwnerName,
                password = password,
                setPassword = setPassword,
            )
        }
    }
}

@Composable
private fun CardInformationInputFields(
    cardNumber: String,
    setCardNumber: (String) -> Unit,
    expiredDate: String,
    setExpiredDate: (String) -> Unit,
    ownerName: String,
    setOwnerName: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        OutlinedTextField(
            value = cardNumber,
            onValueChange = setCardNumber,
            label = { Text(stringResource(R.string.new_card_card_number_label)) },
            placeholder = { Text(stringResource(R.string.new_card_card_number_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = expiredDate,
            onValueChange = setExpiredDate,
            label = { Text(stringResource(R.string.new_card_expiration_day_label)) },
            placeholder = { Text(stringResource(R.string.new_card_expiration_day_placeholder)) },
            modifier = Modifier.fillMaxWidth(fraction = 0.5f),
        )

        OutlinedTextField(
            value = ownerName,
            onValueChange = setOwnerName,
            label = { Text(stringResource(R.string.new_card_card_owner_name_label)) },
            placeholder = { Text(stringResource(R.string.new_card_card_owner_name_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
        )

        OutlinedTextField(
            value = password,
            onValueChange = setPassword,
            label = { Text(stringResource(R.string.new_card_password_label)) },
            placeholder = { Text(stringResource(R.string.new_card_password_placeholder)) },
            modifier = Modifier.fillMaxWidth(fraction = 0.5f),
            visualTransformation = PasswordVisualTransformation(),
        )
    }
}

@Preview
@Composable
private fun NewCardScreenPreview() {
    NewCardScreen(
        cardNumber = "0000 - 0000 - 0000 - 0000",
        expiredDate = "00 / 00",
        ownerName = "홍길동",
        password = "0000",
        setCardNumber = {},
        setExpiredDate = {},
        setOwnerName = {},
        setPassword = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun CardInformationInputFieldsPreview() {
    PaymentsTheme {
        CardInformationInputFields(
            cardNumber = "",
            setCardNumber = {},
            expiredDate = "",
            setExpiredDate = {},
            ownerName = "",
            setOwnerName = {},
            password = "",
            setPassword = {}
        )
    }
}
