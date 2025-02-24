package nextstep.payments.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.model.BankType
import nextstep.payments.ui.newcard.component.BankSelectBottomSheet
import nextstep.payments.ui.newcard.component.CardExpiredDateTextFiled
import nextstep.payments.ui.newcard.component.CardNumberTextFiled
import nextstep.payments.ui.newcard.component.CardOwnerNameTextFiled
import nextstep.payments.ui.newcard.component.CardPasswordTextFiled
import nextstep.payments.ui.newcard.component.NewCardTopBar


@Composable
fun CardInfoScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    bankType: BankType,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    setBankType: (BankType) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { NewCardTopBar(onBackClick = onBackClick, onSaveClick = onSaveClick) },
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

            PaymentCard(type = bankType)

            Spacer(modifier = Modifier.height(10.dp))

            CardNumberTextFiled(value = cardNumber, onValueChange = setCardNumber)
            CardExpiredDateTextFiled(value = expiredDate, onValueChange = setExpiredDate)
            CardOwnerNameTextFiled(value = ownerName, onValueChange = setOwnerName)
            CardPasswordTextFiled(value = password, onValueChange = setPassword)
        }
        BankSelectBottomSheet(selectedBank = bankType, onItemClick = setBankType)
    }
}

@Preview
@Composable
private fun CardInfoScreenPreview() {
    PaymentsTheme {
        CardInfoScreen(
            cardNumber = "0000000000000000",
            expiredDate = "0000",
            ownerName = "홍길동",
            password = "0000",
            bankType = BankType.NOT_SELECTED,
            setCardNumber = {},
            setExpiredDate = {},
            setOwnerName = {},
            setPassword = {},
            setBankType = {},
            onSaveClick = {},
            onBackClick = {}
        )
    }
}