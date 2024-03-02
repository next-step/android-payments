package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.domain.PaymentCard
import nextstep.payments.ui.payments.mockPaymentCard

@Composable
fun EmptyPaymentCard(
    modifier: Modifier = Modifier,
    cardColor: Color = Color(0xFF333333)
) {
    PaymentCardLayout(
        modifier = modifier,
        cardColor = cardColor,
        content = {
            ChipBox(Modifier.padding(top = 44.dp, start = 14.dp))
        }
    )
}

@Composable
fun PaymentCard(
    payment: PaymentCard,
    modifier: Modifier = Modifier,
    cardColor: Color = Color(0xFF333333)
) {
    PaymentCardLayout(
        modifier = modifier.testTag("PaymentCard"),
        cardColor = cardColor,
        content = {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 14.dp, end = 14.dp, top = 44.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                ChipBox()
                MaskedCardNumber(
                    modifier = Modifier.fillMaxWidth(),
                    cardNumber = payment.cardNumber
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = payment.ownerName,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    FormattedDateText(text = payment.expirationDate)
                }
            }
        }
    )
}

@Composable
private fun MaskedCardNumber(
    cardNumber: String,
    modifier: Modifier = Modifier
) {
    val maskedCardNumber = cardNumber.take(8) + "*".repeat(8)
    val formattedText = creditCardFilter(AnnotatedString(maskedCardNumber)).text
    Text(
        modifier = modifier.fillMaxWidth(),
        text = formattedText,
        fontSize = 12.sp,
        color = Color.White,
        maxLines = 1
    )
}

@Composable
private fun FormattedDateText(
    text: String,
    modifier: Modifier = Modifier
) {
    val formattedText = expirationDateFilter(AnnotatedString(text)).text
    Text(
        modifier = modifier,
        text = formattedText,
        fontSize = 12.sp,
        color = Color.White,
    )
}

@Composable
fun AddCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PaymentCardLayout(
        modifier = modifier.testTag("AddCard"),
        cardColor = Color(0xFFE5E5E5),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(id = R.string.payments_add_card))
            }
        }
    )
}

@Composable
private fun PaymentCardLayout(
    modifier: Modifier = Modifier,
    cardColor: Color,
    content: @Composable () -> Unit = {}
) {
    Card(
        modifier = modifier.size(width = 208.dp, height = 124.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        content()
    }
}

@Composable
private fun ChipBox(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 40.dp, height = 26.dp)
            .background(
                color = Color(0xFFCBBA64),
                shape = RoundedCornerShape(4.dp)
            )
    )
}

@Preview(showBackground = true)
@Composable
private fun ChipBoxPreview() {
    ChipBox()
}

@Preview(showBackground = true)
@Composable
private fun EmptyPaymentCardPreview() {
    EmptyPaymentCard()
}

@Preview
@Composable
fun AddCardPreview() {
    AddCard({})
}

@Preview(showBackground = true)
@Composable
private fun PaymentCardPreview() {
    PaymentCard(payment = mockPaymentCard())
}
