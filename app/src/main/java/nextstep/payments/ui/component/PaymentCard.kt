package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.ui.model.CreditCardType
import nextstep.payments.ui.newcard.model.CardCompany
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun PaymentCard(creditCardType: CreditCardType, modifier: Modifier = Modifier) {
    when (creditCardType) {
        is CreditCardType.AddingCard -> {
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .shadow(8.dp)
                    .background(
                        color = Color(creditCardType.cardCompany.color),
                        shape = RoundedCornerShape(5.dp),
                    )
                    .padding(horizontal = 14.dp, vertical = 14.dp)
            ) {

                CardCompanyName(
                    name = creditCardType.cardCompany.name,
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                Box(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(4.dp),
                        )
                )

                Spacer(modifier = Modifier.size(54.dp))

            }
        }

        is CreditCardType.RegisteredCard -> {

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .shadow(8.dp)
                    .background(
                        color = Color(creditCardType.cardCompany.color),
                        shape = RoundedCornerShape(5.dp),
                    )
                    .padding(horizontal = 14.dp, vertical = 14.dp)
            ) {

                CardCompanyName(
                    name = creditCardType.cardCompany.name,
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                Box(
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .size(width = 40.dp, height = 26.dp)
                        .background(
                            color = Color(0xFFCBBA64),
                            shape = RoundedCornerShape(4.dp),
                        )
                )

                CreditCardInfo(
                    registeredCard = creditCardType
                )
            }

        }
    }
}

@Composable
fun PaymentCard(
    creditCardType: CreditCardType,
    cardName: String,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .shadow(8.dp)
            .background(
                color = Color(getCardColor(cardName)),
                shape = RoundedCornerShape(5.dp),
            )
            .padding(horizontal = 14.dp, vertical = 14.dp)
    ) {

        CardCompanyName(name = cardName, modifier = Modifier.padding(bottom = 15.dp))

        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .size(width = 40.dp, height = 26.dp)
                .background(
                    color = Color(0xFFCBBA64),
                    shape = RoundedCornerShape(4.dp),
                )
        )
        when (creditCardType) {
            is CreditCardType.RegisteredCard -> {
                CreditCardInfo(
                    registeredCard = creditCardType
                )
            }

            is CreditCardType.AddingCard -> {
                Spacer(modifier = Modifier.size(54.dp))
            }

        }
    }
}

@Composable
private fun CreditCardInfo(
    registeredCard: CreditCardType.RegisteredCard,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        CardNumber(registeredCard.number)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CardOwnerName(registeredCard.ownerName)
            CardExpireDate(registeredCard.expiredDate)
        }
    }
}

@Composable
private fun CardExpireDate(expiredDate: String) {
    Text(
        text = "${expiredDate.substring(0, 2)} / ${expiredDate.substring(2)}",
        color = Color.White,
        fontSize = 12.sp,
    )
}

@Composable
private fun CardOwnerName(ownerName: String) {
    Text(
        text = ownerName,
        color = Color.White,
        fontSize = 12.sp,
    )
}

@Composable
private fun CardNumber(cardNumber: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = cardNumber.substring(0, 4),
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = "-",
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = cardNumber.substring(4, 8),
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = "-",
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = "****",
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = "-",
            color = Color.White,
            fontSize = 12.sp
        )
        Text(
            text = "****",
            color = Color.White,
            fontSize = 12.sp
        )
    }
}

@Composable
fun CardCompanyName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier,
        color = Color.White,
        fontSize = 12.sp
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        PaymentCard(
            creditCardType = CreditCardType.AddingCard(CardCompany(R.drawable.ic_kakao, "카카오뱅크",0xF444444)),
            cardName = "신한카드",
            Modifier.padding(horizontal = 76.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CreditCardInfoPreview() {
    PaymentsTheme {
        PaymentCard(
            creditCardType = CreditCardType.RegisteredCard(
                "1234567812345678",
                "0421",
                "김무현",
                "1234",
                CardCompany(R.drawable.ic_kakao, "카카오뱅크",0xF444444)
            ),
            cardName = "신한카드",
            Modifier.padding(horizontal = 76.dp)
        )
    }
}

private fun getCardColor(cardName: String): Long {
    when (cardName) {

    }
    return 0xFFF04651
}
