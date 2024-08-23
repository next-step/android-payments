package nextstep.payments.ui.card.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.ui.card.list.component.CardListTopBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun CardListScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { CardListTopBar() }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}

@Preview
@Composable
private fun CardListScreenPreview() {
    PaymentsTheme {
        CardListScreen()
    }
}
