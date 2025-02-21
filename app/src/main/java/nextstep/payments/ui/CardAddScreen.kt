package nextstep.payments.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.data.model.CardModel
import nextstep.payments.ui.component.Card
import nextstep.payments.ui.component.CardAddTopBar
import nextstep.payments.ui.component.CardInputField
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.utils.toCardList

@Composable
fun CardAddScreen(
    cardModel: CardModel,
    cardAdded: Boolean,
    onCardNumberChange: (String) -> Unit,
    onExpiredDateChange: (String) -> Unit,
    onOwnerNameChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onBackPressed: () -> Unit,
    onAddClicked: () -> Unit,
) {

    val context = LocalContext.current

    LaunchedEffect(cardAdded) {
        if (cardAdded) context.toCardList()
    }

    Scaffold(
        topBar = {
            CardAddTopBar(
                onBackClick = onBackPressed,
                onCheckClick = { onAddClicked() },
            )
        },
    ) { innerPadding ->

        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            Card(model = cardModel)

            Spacer(modifier = Modifier.height(10.dp))

            CardInputField(
                value = cardModel.number,
                onValueChange = onCardNumberChange,
                labelText = stringResource(R.string.card_number_label),
                placeHolderText = stringResource(R.string.card_number_placeholder),
            )

            CardInputField(
                value = cardModel.expiredDate,
                onValueChange = onExpiredDateChange,
                labelText = stringResource(R.string.card_expired_date_label),
                placeHolderText = stringResource(R.string.card_expired_date_placeholder),
            )

            CardInputField(
                value = cardModel.ownerName,
                onValueChange = onOwnerNameChange,
                labelText = stringResource(R.string.card_owner_name_label),
                placeHolderText = stringResource(R.string.card_owner_name_placeholder),
            )

            CardInputField(
                value = cardModel.password,
                onValueChange = onPasswordChange,
                labelText = stringResource(R.string.card_password_label),
                placeHolderText = stringResource(R.string.card_password_placeholder),
            )
        }
    }
}

@Preview
@Composable
private fun StatelessNewCardScreenPreview() {
    PaymentsTheme {
        CardAddScreen(
            cardModel = CardModel(
                number = "0000 - 0000 - 0000 - 0000",
                expiredDate = "00 / 00",
                ownerName = "홍길동",
                password = "0000",
            ),
            cardAdded = true,
            onCardNumberChange = {},
            onExpiredDateChange = {},
            onOwnerNameChange = {},
            onPasswordChange = {},
            onBackPressed = {},
            onAddClicked = {},
        )
    }
}