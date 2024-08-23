package nextstep.payments.ui.card.list.component.card

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CardOwnerName(ownerName: String, modifier: Modifier = Modifier) {
    Text(
        text = ownerName,
        modifier = modifier,
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White
    )
}
