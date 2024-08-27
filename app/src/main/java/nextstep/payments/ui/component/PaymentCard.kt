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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AddPaymentCard(
    modifier: Modifier = Modifier,
) {
    DefaultCard(
        modifier = modifier,
        color =Color(0xFFE5E5E5),
        content = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { /* Click Action */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
    )
}

@Composable
fun DefaultPaymentCard(
    modifier: Modifier = Modifier,
) {
    DefaultCard(
        modifier = modifier,
        content = {
            CardIcChip(Modifier.padding(start = 14.dp, bottom = 10.dp))
        }
    )
}

@Composable
fun RegisteredPaymentCard(
    modifier: Modifier = Modifier,
) {
    DefaultCard(
        modifier = modifier,
        content = {

            Column(
                Modifier
                    .fillMaxSize()
                    .padding(start = 14.dp, end = 14.dp, top = 44.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                CardIcChip()
                Text(
                    modifier = modifier.fillMaxWidth(),
                    text = "1234-5678-1234-5678",
                    fontSize = 12.sp,
                    color = Color.White,
                    maxLines = 1
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "JINHYUK JANG",
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Text(
                        modifier = modifier,
                        text = "00/00",
                        fontSize = 12.sp,
                        color = Color.White,
                    )
                }
            }
        }
    )
}

@Composable
fun DefaultCard(
    modifier: Modifier = Modifier,
    color:Color = Color(0xFF333333),
    content: @Composable () -> Unit = {}
) {
    Box(
        contentAlignment = Alignment.CenterStart,
        modifier = modifier
            .shadow(8.dp)
            .size(width = 208.dp, height = 124.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(5.dp),
            )
    ) {
        content()
    }
}

@Composable
fun CardIcChip(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(width = 40.dp, height = 26.dp)
            .background(
                color = Color(0xFFCBBA64),
                shape = RoundedCornerShape(4.dp),
            )
    )
}

@Preview(showBackground = true)
@Composable
private fun AddPaymentCardPreview() {
    AddPaymentCard()
}

@Preview(showBackground = true)
@Composable
private fun DefaultPaymentCardPreview() {
    DefaultPaymentCard()
}

@Preview(showBackground = true)
@Composable
private fun RegisteredPaymentCardPreview() {
    RegisteredPaymentCard()
}
