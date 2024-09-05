package nextstep.payments.ui.component.card.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.component.card.PaymentCard
import nextstep.payments.ui.component.text.input.CardNumberVisualTransformation
import nextstep.payments.ui.component.text.input.ExpirationDateVisualTransformation
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun EditableCardPage(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bank: CardBankInformation,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Spacer(modifier = Modifier.height(14.dp))

        PaymentCard(cardBankInformation = bank)

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = cardNumber,
            onValueChange = { newValue -> setCardNumber(newValue) },
            label = { Text(text = stringResource(id = R.string.card_number)) },
            placeholder = { Text(text = stringResource(id = R.string.card_number_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = CardNumberVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number
            )
        )

        OutlinedTextField(
            value = expiredDate,
            onValueChange = { newValue -> setExpiredDate(newValue) },
            label = { Text(text = stringResource(id = R.string.expiry_date)) },
            placeholder = { Text(text = stringResource(id = R.string.expiry_date_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = ExpirationDateVisualTransformation(),
        )

        OutlinedTextField(
            value = ownerName,
            onValueChange = setOwnerName,
            label = { Text(text = stringResource(id = R.string.card_owner_name)) },
            placeholder = { Text(text = stringResource(id = R.string.card_owner_name_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { newValue -> setPassword(newValue) },
            label = { Text(text = stringResource(R.string.password)) },
            placeholder = { Text(text = stringResource(R.string.password_placeholder)) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            )
        )
    }
}

// Card information data class for previews
private data class CardPreviewData(
    val cardNumber: String,
    val expiredDate: String,
    val ownerName: String,
    val password: String,
    val bank: CardBankInformation
)

private class CardPreviewProvider : PreviewParameterProvider<CardPreviewData> {
    override val values = sequenceOf(
        CardPreviewData(
            cardNumber = "1111222233334444",
            expiredDate = "1234",
            ownerName = "홍길동",
            password = "1234",
            bank = CardBankInformation.None
        ),
        CardPreviewData(
            cardNumber = "",
            expiredDate = "",
            ownerName = "",
            password = "",
            bank = CardBankInformation.None
        ),
    ) + CardBankInformation.entries.map {
        CardPreviewData(
            cardNumber = "",
            expiredDate = "",
            ownerName = "",
            password = "",
            bank = it
        )
    }
}

@Preview
@Composable
private fun EditableCardPagePreview(
    @PreviewParameter(CardPreviewProvider::class) cardPreviewData: CardPreviewData
) {
    val cardNumber = remember { mutableStateOf(cardPreviewData.cardNumber) }
    val expiredDate = remember { mutableStateOf(cardPreviewData.expiredDate) }
    val ownerName = remember { mutableStateOf(cardPreviewData.ownerName) }
    val password = remember { mutableStateOf(cardPreviewData.password) }

    PaymentsTheme {
        EditableCardPage(
            cardNumber = cardNumber.value,
            expiredDate = expiredDate.value,
            ownerName = ownerName.value,
            password = password.value,
            bank = cardPreviewData.bank,
            setCardNumber = { cardNumber.value = it },
            setExpiredDate = { expiredDate.value = it },
            setOwnerName = { ownerName.value = it },
            setPassword = { password.value = it },
            modifier = Modifier.fillMaxSize()
        )
    }
}