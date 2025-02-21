package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.designsystem.theme.AddButtonColor
import nextstep.payments.designsystem.theme.PaymentsTheme

@Composable
fun AddCardButton(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onAddClick,
        modifier = modifier
            .width(208.dp)
            .height(124.dp)
            .background(
                color = AddButtonColor, RoundedCornerShape(5.dp)
            )
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "add_card_icon"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AddCardButtonPreview() {
    PaymentsTheme {
        AddCardButton(onAddClick = {})
    }
}