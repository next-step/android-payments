package nextstep.payments.ui.cards.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun EmptyCardComponent(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(32.dp)
            .semantics {
                contentDescription = "EmptyCard"
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "새로운 카드를 등록해주세요",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

        NewCard(
            modifier = Modifier.padding(top = 32.dp, start = 52.dp, end = 52.dp),
            onClick = onAddClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyCardComponentPreview() {
    PaymentsTheme {
        EmptyCardComponent(onAddClick = {})
    }
}