package nextstep.payments.component.card

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun AdditionCard(
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
) {
    CardFrame(
        modifier = modifier,
        backgroundColor = Color(0xFFE5E5E5),
        onClick = onClick
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "+",
            fontSize = 34.sp,
            color = Color(0xFF575757)
        )
    }
}

@Preview(showBackground = true, name = "AdditionCard")
@Composable
private fun Preview() {
    PaymentsTheme {
        AdditionCard(
            onClick = {

            }
        )
    }
}