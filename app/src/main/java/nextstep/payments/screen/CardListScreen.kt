package nextstep.payments.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.view.CardListTopBar

@Composable
fun CardListScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                onAddClick = {}
            )
        },
        modifier = modifier
    ) { innerPadding ->
        CardList(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun CardList(
    modifier: Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {

    }
}

@Preview
@Composable
fun NewCardScreenPreview(
) {
    CardListScreen(

    )
}