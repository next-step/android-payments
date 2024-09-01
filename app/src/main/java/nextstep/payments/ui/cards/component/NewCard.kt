package nextstep.payments.ui.cards.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(5.dp),
        modifier = modifier
            .aspectRatio(208f / 124f)
            .defaultMinSize(
                minWidth = 208.dp,
                minHeight = 124.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE5E5E5)
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "addCard",
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Preview
@Composable
private fun NewCardPreview() {
    PaymentsTheme {
        NewCard(
            modifier = Modifier
                .padding(top = 32.dp)
                .width(208.dp)
                .height(124.dp),
            onClick = {},
        )
    }
}