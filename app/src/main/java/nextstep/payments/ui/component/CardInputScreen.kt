package nextstep.payments.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.data.model.BankType
import nextstep.payments.ui.add.BankSelectBottomSheet
import nextstep.payments.ui.util.toCardExpiredDateTransformedText
import nextstep.payments.ui.util.toCardNumberTransformedText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun CardInputScreen(
    title: String,
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bankType: BankType,
    sheetOpened: Boolean,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    setBankType: (BankType) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var bankSelectSheetOpened by remember { mutableStateOf(sheetOpened) }

    Scaffold(
        topBar = {
            CardDetailTopBar(
                title = title,
                onBackClick = onBackClick,
                onSaveClick = onSaveClick
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

            PaymentCard(
                bankType = bankType,
                number = cardNumber,
                expiredDate = expiredDate,
                ownerName = ownerName,
                onClick = { bankSelectSheetOpened = true }
            )

            Spacer(modifier = Modifier.height(10.dp))

            CardInputField(
                value = cardNumber,
                onValueChange = setCardNumber,
                label = stringResource(R.string.card_number),
                placeholder = stringResource(R.string.card_number_place_holder),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                visualTransformation = { cardNumber.toCardNumberTransformedText() },
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "카드 번호 입력"
                    },
            )

            CardInputField(
                value = expiredDate,
                onValueChange = setExpiredDate,
                label = stringResource(R.string.expired_date),
                placeholder = stringResource(R.string.expired_date_place_holder),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                visualTransformation = { expiredDate.toCardExpiredDateTransformedText() },
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "만료일 입력"
                    },
            )

            CardInputField(
                value = ownerName,
                onValueChange = setOwnerName,
                label = stringResource(R.string.owner_name_label),
                placeholder = stringResource(R.string.owner_name_place_holder),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "카드 소유자 입력"
                    },
            )

            CardInputField(
                value = password,
                onValueChange = setPassword,
                label = stringResource(R.string.password),
                placeholder = stringResource(R.string.password_place_holder),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics {
                        contentDescription = "비밀번호 입력"
                    },
            )
        }
        if (bankSelectSheetOpened) {
            BankSelectBottomSheet(
                onBankSelect = setBankType,
                onDismissRequest = { bankSelectSheetOpened = false }
            )
        }
    }
}

@Preview
@Composable
private fun CardInputScreenPreview() {
    CardInputScreen(
        title = "카드 추가",
        cardNumber = "0000111122223333",
        expiredDate = "1234",
        ownerName = "Miriam Steele",
        password = "12345",
        bankType = BankType.BC,
        sheetOpened = false,
        setCardNumber = {},
        setExpiredDate = {},
        setOwnerName = {},
        setPassword = {},
        setBankType = {},
        onBackClick = {},
        onSaveClick = {},
    )
}
