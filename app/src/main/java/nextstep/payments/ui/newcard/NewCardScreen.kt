package nextstep.payments.ui.newcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
import nextstep.payments.ui.newcard.component.BankBottomSheetContent
import nextstep.payments.ui.newcard.component.NewCardTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NewCardScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bank: CardBankInformation,
    isShowBanks: Boolean,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onAddCardClick: () -> Unit,
    onBackClick: () -> Unit,
    onBankClick: (CardBankInformation) -> Unit,
    modifier: Modifier = Modifier,
) {
    val bottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )
    LaunchedEffect(key1 = isShowBanks) {
        if (isShowBanks) {
            bottomSheetState.show()
        } else {
            bottomSheetState.hide()
        }
    }

    // 카드 추가 화면
    Scaffold(
        modifier = modifier,
        topBar = {
            NewCardTopBar(
                onBackClick = onBackClick,
                onSaveClick = onAddCardClick
            )
        },
        content = { innerPadding ->
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 24.dp)
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
        },
    )

    if (isShowBanks) {
        ModalBottomSheet(
            modifier = Modifier.semantics {
                contentDescription = "BankBottomSheet"
            },
            onDismissRequest = { },
            sheetState = bottomSheetState,
        ) {
            BankBottomSheetContent(
                onBankClick = {
                    onBankClick(it)
                },
            )
        }
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
private fun PreviewNewCardScreen(
    @PreviewParameter(CardPreviewProvider::class) cardPreviewData: CardPreviewData
) {
    val cardNumber = remember { mutableStateOf(cardPreviewData.cardNumber) }
    val expiredDate = remember { mutableStateOf(cardPreviewData.expiredDate) }
    val ownerName = remember { mutableStateOf(cardPreviewData.ownerName) }
    val password = remember { mutableStateOf(cardPreviewData.password) }

    NewCardScreen(
        cardNumber = cardNumber.value,
        expiredDate = expiredDate.value,
        ownerName = ownerName.value,
        password = password.value,
        bank = cardPreviewData.bank,
        isShowBanks = false,
        setCardNumber = { cardNumber.value = it },
        setExpiredDate = { expiredDate.value = it },
        setOwnerName = { ownerName.value = it },
        setPassword = { password.value = it },
        onAddCardClick = {},
        onBackClick = {},
        onBankClick = {},
        modifier = Modifier.fillMaxSize()
    )
}