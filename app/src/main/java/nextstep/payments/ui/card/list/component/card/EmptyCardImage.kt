package nextstep.payments.ui.card.list.component.card

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.card.registration.NewCardActivity
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun EmptyCardImage(cardColor: Color, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        modifier = modifier
            .clickable {
                val intent = Intent(context, NewCardActivity::class.java).apply {
                }
                context.startActivity(intent)
            },
        colors = CardDefaults.cardColors(
            containerColor = cardColor,
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun EmptyCardImagePreview() {
    PaymentsTheme {
        EmptyCardImage(
            cardColor = Color(0xFFE5E5E5),
            modifier = Modifier
                .size(width = 208.dp, height = 124.dp)
        )
    }
}
