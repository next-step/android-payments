package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.ext.cardDefaultSize
import nextstep.payments.ui.theme.EmptyCardBgColor


@Composable
fun EmptyCardItem(
    onClickItem: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .cardDefaultSize()
            .clip(RoundedCornerShape(5.dp))
            .background(EmptyCardBgColor)
            .clickable { onClickItem() }
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "",
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyCardItemPreview() {
    EmptyCardItem(
        onClickItem = {}
    )
}
