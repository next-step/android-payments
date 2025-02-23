package nextstep.payments.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.R

@Composable
internal fun CardListScreen(
    cardListViewModel: CardListViewModel,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardListUiState by cardListViewModel.uiState.collectAsStateWithLifecycle()

    CardListScreen(
        cardListUiState = cardListUiState,
        onAddCardClick = onAddCardClick,
        modifier = modifier,
    )
}

@Composable
internal fun CardListScreen(
    cardListUiState: CardListUiState,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopBar() },
        modifier = modifier
    ) { innerPadding ->
        when (cardListUiState) {
            CardListUiState.Empty -> EmptyListContent(
                onAddCardClick = onAddCardClick,
                modifier = Modifier.padding(innerPadding)
            )

            else -> {}
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF1D1B20)
            )
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun StatefulCardListScreenPreview_Empty() {
    CardListScreen(
        cardListUiState = CardListUiState.Empty,
        onAddCardClick = {}
    )
}
