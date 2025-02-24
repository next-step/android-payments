package nextstep.payments.ui.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.component.NewPaymentCard

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
        NewPaymentCard(
            onClick = onAddCardClick
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyListContentPreview() {
    EmptyListContent(
        onAddCardClick = {}
    )
}
