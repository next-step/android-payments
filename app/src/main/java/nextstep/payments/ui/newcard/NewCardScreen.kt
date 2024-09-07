package nextstep.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.newcard.component.NewCardTextField
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    setCardNumber: (String) -> Unit,
    setPassword: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onSaveClick,
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp),
        ) {
            Spacer(modifier = Modifier.height(14.dp))
            PaymentCard()
            Spacer(modifier = Modifier.height(10.dp))
            NewCardTextField(
                label = stringResource(R.string.cardNumber_label),
                placeHolder = stringResource(R.string.cardNumber_place_holder),
                text = cardNumber,
                setText = setCardNumber,
            )
            NewCardTextField(
                label = stringResource(R.string.expiredDate_label),
                placeHolder = stringResource(R.string.expiredDate_place_holder),
                text = expiredDate,
                setText = setExpiredDate,
            )
            NewCardTextField(
                label = stringResource(R.string.ownerName_label),
                placeHolder = stringResource(R.string.ownerName_place_holder),
                text = ownerName,
                setText = setOwnerName,
            )
            NewCardTextField(
                label = stringResource(R.string.password_label),
                placeHolder = stringResource(R.string.password_place_holder),
                text = password,
                setText = setPassword,
                visualTransformation = PasswordVisualTransformation(),
            )
        }
    }
}

@Preview
@Composable
private fun NewCardScreenPreview() {
    PaymentsTheme {
        NewCardScreen(
            cardNumber = "cardNumber",
            expiredDate = "expiredDate",
            ownerName = "ownerName",
            password = "password",
            setCardNumber = { },
            setPassword = { },
            setOwnerName = { },
            setExpiredDate = { },
            onBackClick = { },
            onSaveClick = { },
        )
    }
}
