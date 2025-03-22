package nextstep.payments.common.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nextstep.payments.common.component.PaymentCard
import nextstep.payments.common.model.Card
import nextstep.payments.common.model.CardCompany
import nextstep.payments.newcard.component.CardCompanySelectBottomSheet
import nextstep.payments.newcard.component.CardNumberTextField
import nextstep.payments.newcard.component.ExpiredDateTextField
import nextstep.payments.common.component.CardFormTopBar
import nextstep.payments.newcard.component.OwnerNameTextField
import nextstep.payments.newcard.component.PasswordTextField
import nextstep.payments.newcard.model.Validation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardFormScreen(
    title: String,
    card: Card,
    cardNumberValidation: Validation,
    expiredDateValidation: Validation,
    passwordValidation: Validation,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onClickCard: () -> Unit,
    showBottomSheet: Boolean,
    onDismissRequest: () -> Unit,
    sheetState: SheetState,
    onClickCardCompany: (CardCompany) -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CardFormTopBar(
                title = title,
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

            PaymentCard(
                modifier = Modifier.clickable { onClickCard() },
                cardCompany = card.cardCompany
            )

            Spacer(modifier = Modifier.height(10.dp))

            CardNumberTextField(
                modifier = Modifier.fillMaxWidth(),
                cardNumber = card.cardNumber,
                validation = cardNumberValidation,
                setCardNumber = setCardNumber
            )

            ExpiredDateTextField(
                modifier = Modifier.fillMaxWidth(),
                expiredDate = card.expiredDate,
                validation = expiredDateValidation,
                setExpiredDate = setExpiredDate
            )

            OwnerNameTextField(
                modifier = Modifier.fillMaxWidth(),
                ownerName = card.ownerName,
                setOwnerName = setOwnerName
            )

            PasswordTextField(
                modifier = Modifier.fillMaxWidth(),
                password = card.password,
                validation = passwordValidation,
                setPassword = setPassword
            )
        }

        if (showBottomSheet) {
            CardCompanySelectBottomSheet(
                sheetState = sheetState,
                onClickCardCompany = onClickCardCompany,
                onDismissRequest = onDismissRequest,
            )
        }
    }
}
