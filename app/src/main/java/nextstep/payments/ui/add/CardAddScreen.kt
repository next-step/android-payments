package nextstep.payments.ui.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.R
import nextstep.payments.data.repository.PaymentCardsRepository
import nextstep.payments.ui.component.CardDetailTopBar
import nextstep.payments.ui.component.CardInputField
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.ui.util.toCardExpiredDateTransformedText
import nextstep.payments.ui.util.toCardNumberTransformedText

@Composable
internal fun CardAddScreen(
    cardAddViewModel: CardAddViewModel,
    onBackClick: () -> Unit,
    onSaveCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val card by cardAddViewModel.card.collectAsStateWithLifecycle()
    val cardAdded by cardAddViewModel.cardAdded.collectAsStateWithLifecycle()

    LaunchedEffect(cardAdded) {
        if (cardAdded) onSaveCard()
    }

    CardAddScreen(
        cardNumber = card.number,
        expiredDate = card.expiredDate,
        ownerName = card.ownerName,
        password = card.password,
        setCardNumber = cardAddViewModel::setCardNumber,
        setExpiredDate = cardAddViewModel::setExpiredDate,
        setOwnerName = cardAddViewModel::setOwnerName,
        setPassword = cardAddViewModel::setPassword,
        onBackClick = onBackClick,
        onSaveClick = cardAddViewModel::addCard,
        modifier = modifier
    )
}

@Composable
internal fun CardAddScreen(
    cardNumber: String,
    expiredDate: String,
    ownerName: String,
    password: String,
    setCardNumber: (String) -> Unit,
    setExpiredDate: (String) -> Unit,
    setOwnerName: (String) -> Unit,
    setPassword: (String) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CardDetailTopBar(
                title = stringResource(R.string.add_card),
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

            PaymentCard()

            Spacer(modifier = Modifier.height(10.dp))

            CardInputField(
                value = cardNumber,
                onValueChange = setCardNumber,
                label = stringResource(R.string.card_number),
                placeholder = stringResource(R.string.card_number_place_holder),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                visualTransformation = { cardNumber.toCardNumberTransformedText() },
                modifier = Modifier.fillMaxWidth(),
            )

            CardInputField(
                value = expiredDate,
                onValueChange = setExpiredDate,
                label = stringResource(R.string.expired_date),
                placeholder = stringResource(R.string.expired_date_place_holder),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                visualTransformation = { expiredDate.toCardExpiredDateTransformedText() },
                modifier = Modifier.fillMaxWidth(),
            )

            CardInputField(
                value = ownerName,
                onValueChange = setOwnerName,
                label = stringResource(R.string.owner_name_label),
                placeholder = stringResource(R.string.owner_name_place_holder),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                modifier = Modifier.fillMaxWidth(),
            )

            CardInputField(
                value = password,
                onValueChange = setPassword,
                label = stringResource(R.string.password),
                placeholder = stringResource(R.string.password_place_holder),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StatefulCardAddScreenPreview() {
    CardAddScreen(
        cardAddViewModel = CardAddViewModel(PaymentCardsRepository).apply {
            setCardNumber("00001111222233333")
            setExpiredDate("0000")
            setOwnerName("홍길동")
            setPassword("0000")
        },
        onBackClick = {},
        onSaveCard = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun StatelessCardAddScreenPreview() {
    CardAddScreen(
        cardNumber = "00001111222233333",
        expiredDate = "0000",
        ownerName = "홍길동",
        password = "0000",
        setCardNumber = { },
        setExpiredDate = { },
        setOwnerName = { },
        setPassword = { },
        onBackClick = {},
        onSaveClick = {}
    )
}
