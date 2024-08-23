package nextstep.payments.ui.card.list.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nextstep.payments.data.Card

@Composable
fun CardOwnerName(ownerName: String, modifier: Modifier = Modifier) {
    Text(
        text = ownerName,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White
    )
}
