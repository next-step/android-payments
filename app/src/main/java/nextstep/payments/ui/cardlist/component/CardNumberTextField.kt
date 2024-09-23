package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.theme.body

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
            style = body,
        )
        Text(
            text = "-",
            style = body,
        )
        Text(
            text = secondOfCardNumber,
            style = body,
        )
        Text(
            text = "-",
            style = body,
        )
        Text(
            text = "****",
            style = body,
        )
        Text(
            text = "-",
            style = body,
        )
        Text(
            text = "****",
            style = body,
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
