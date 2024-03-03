package nextstep.payments.card.add

import android.app.Activity
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.card.Card
import nextstep.payments.card.CardExpireDateFormatter
import nextstep.payments.card.DefaultCardRepository
import nextstep.payments.card.add.component.AddingCardScreenTopBar
import nextstep.payments.card.add.component.CardCvcInputField
import nextstep.payments.card.add.component.CardExpireDateInputField
import nextstep.payments.card.add.component.CardNumberInputField
import nextstep.payments.card.add.component.CardOwnerNameInputField
import nextstep.payments.card.add.component.CardPasswordInputField
import nextstep.payments.card.component.PaymentCard

@Composable
fun AddingCardScreen() {
    val context = LocalContext.current

    val (getCardNumber, setCardNumber) = remember { mutableStateOf("") }
    val (getCardExpireDate, setCardExpireDate) = remember { mutableStateOf("") }
    val (getCardOwnerName, setCardOwnerName) = remember { mutableStateOf("") }
    val (getCardCvc, setCardCvc) = remember { mutableStateOf("") }
    val (getCardPassword, setCardPassword) = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            AddingCardScreenTopBar(
                onBackButtonClick = { finish(context) },
                onSaveButtonClick = {
                    val cardExpireDate = CardExpireDateFormatter.toDate(getCardExpireDate)
                        ?: return@AddingCardScreenTopBar

                    DefaultCardRepository.addCard(
                        Card(
                            cardNumber = getCardNumber,
                            expireDate = cardExpireDate,
                            ownerName = getCardOwnerName,
                            cvcNumber = getCardCvc,
                            password = getCardPassword,
                        )
                    )
                    finish(context)
                },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .verticalScroll(state = rememberScrollState()),
        ) {
            Spacer(modifier = Modifier.height(14.dp))

            PaymentCard(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                cardColor = Color(0xFF333333)
            )

            Spacer(modifier = Modifier.height(28.dp))

            CardNumberInputField(value = getCardNumber, onValueChanged = setCardNumber)

            CardExpireDateInputField(value = getCardExpireDate, onValueChanged = setCardExpireDate)

            CardOwnerNameInputField(value = getCardOwnerName, onValueChanged = setCardOwnerName)

            CardCvcInputField(value = getCardCvc, onValueChanged = setCardCvc)

            CardPasswordInputField(value = getCardPassword, onValueChanged = setCardPassword)
        }
    }
}

private fun finish(context: Context) {
    (context as? Activity)?.finish()
}

@Preview(showBackground = true)
@Composable
private fun AddingCardScreenPreview() {
    AddingCardScreen()
}
