package nextstep.payments.ui.card.list

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CardListScreen(onShowNewCard: () -> Unit) {
    Text(text = "new", Modifier.clickable { onShowNewCard() })
}
