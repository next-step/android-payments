package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R.string.cardlist_empty_card_icon

@Composable
fun CardListEmptyCard(
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(width = 208.dp, height = 124.dp)
            .background(
                shape = RoundedCornerShape(size = 5.dp),
                color = Color.Gray,
            )
            .clickable { onAddCardClick() },
    ) {
        Icon(
            imageVector = Filled.Add,
            contentDescription = stringResource(id = cardlist_empty_card_icon),
            modifier = Modifier.padding(
                horizontal = 94.dp,
                vertical = 50.dp,
            )
        )
    }
}

@Preview
@Composable
private fun CardListEmptyCardPreview() {
    CardListEmptyCard(
        onAddCardClick = { },
    )
}
