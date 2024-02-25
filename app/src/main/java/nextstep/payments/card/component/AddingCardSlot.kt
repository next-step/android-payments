package nextstep.payments.card.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AddingCardSlot(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(
                width = 208.dp,
                height = 124.dp,
            )
            .background(
                shape = RoundedCornerShape(5.dp),
                color = Color(0xFFE5E5E5)
            )
    ) {
        Icon(
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.Center),
            tint = Color(0xFF575757),
            imageVector = Icons.Default.Add,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun AddingCardSlotPreview() {
    AddingCardSlot(
        onClick = {},
    )
}
