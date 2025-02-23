package nextstep.payments.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R

@Composable
internal fun EmptyListContent(
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(32.dp),
    ) {
        Text(
            text = stringResource(R.string.card_add_guide),
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF333333),
            textAlign = TextAlign.Center
        )
        Box(
            modifier = Modifier
                .size(width = 208.dp, height = 124.dp)
                .clip(shape = RoundedCornerShape(5.dp))
                .background(color = Color(0xFFE5E5E5))
                .clickable(onClick = onAddCardClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "+",
                fontSize = 34.sp,
                color = Color(0xFF575757)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyListContentPreview() {
    EmptyListContent(
        onAddCardClick = {}
    )
}
