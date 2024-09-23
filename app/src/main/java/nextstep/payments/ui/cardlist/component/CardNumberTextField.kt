package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.ui.theme.RobotoMedium

@Composable
fun CardNumberTextField(
    firstOfCardNumber: String,
    secondOfCardNumber: String,
    modifier: Modifier = Modifier,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = firstOfCardNumber,
            color = Color.White,
            fontFamily = RobotoMedium,
            fontSize = 12.sp,
        )
        Text(
            text = "-",
            color = Color.White,
            fontFamily = RobotoMedium,
            fontSize = 12.sp,
        )
        Text(
            text = secondOfCardNumber,
            color = Color.White,
            fontFamily = RobotoMedium,
            fontSize = 12.sp,
        )
        Text(
            text = "-",
            color = Color.White,
            fontFamily = RobotoMedium,
            fontSize = 12.sp,
        )
        Text(
            text = "****",
            color = Color.White,
            fontFamily = RobotoMedium,
            fontSize = 12.sp,
        )
        Text(
            text = "-",
            color = Color.White,
            fontFamily = RobotoMedium,
            fontSize = 12.sp,
        )
        Text(
            text = "****",
            color = Color.White,
            fontFamily = RobotoMedium,
            fontSize = 12.sp,
        )
    }
}

@Preview
@Composable
private fun CardNumberTextFieldPreview() {
    CardNumberTextField(
        firstOfCardNumber = "1234",
        secondOfCardNumber = "1234",
        modifier = Modifier.width(width = 208.dp),
    )
}
