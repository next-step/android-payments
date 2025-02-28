package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CreateCardButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(width = 208.dp, height = 124.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(color = Color(0xFFE5E5E5))
            .clickable { onClick.invoke() }
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = Icons.Filled.Add.name
        )
    }
}

@Preview
@Composable
private fun NewCardButtonPreview() {
    PaymentsTheme {
        CreateCardButton({})
    }
}
