package nextstep.payments.screens.card.list.components

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun AddCard(
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(width = 208.dp, height = 124.dp)
            .background(color = Color(0xFFE5E5E5), shape = RoundedCornerShape(5.dp))
            .clip(shape = RoundedCornerShape(5.dp))
            .clickable(onClick = onAddCardClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(R.string.card_list_add_card_description),
        )
    }
}

@Preview
@Composable
private fun AddCardPreview() {
    PaymentsTheme {
        AddCard(
            onAddCardClick = {},
        )
    }
}
