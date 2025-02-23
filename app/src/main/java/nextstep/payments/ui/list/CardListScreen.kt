package nextstep.payments.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.R
import nextstep.payments.data.model.Card

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
        topBar = {
            TopBar(
                cardListUiState = cardListUiState,
                onAddCardClick = onAddCardClick
            )
        },
        modifier = modifier
    ) { innerPadding ->
        when (cardListUiState) {
            is CardListUiState.Empty -> EmptyListContent(
                onAddCardClick = onAddCardClick,
                modifier = Modifier.padding(innerPadding)
            )

            is CardListUiState.One -> OneCardContent(
                card = cardListUiState.card,
                onAddCardClick = onAddCardClick,
                modifier = Modifier.padding(innerPadding)
            )

            is CardListUiState.Many -> {
                ManyCardContent(
                    cards = cardListUiState.cards,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    cardListUiState: CardListUiState,
    onAddCardClick: () -> Unit,
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
        actions = {
            if (cardListUiState is CardListUiState.Many) {
                TextButton(
                    onClick = onAddCardClick
                ) {
                    Text(
                        text = stringResource(R.string.add),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreview_Empty() {
    CardListScreen(
        cardListUiState = CardListUiState.Empty,
        onAddCardClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreview_One() {
    CardListScreen(
        cardListUiState = CardListUiState.One(
            card = Card(
                number = "0000 - 0000 - 0000 - 0000",
                expiredDate = "00 / 00",
                ownerName = "홍길동",
                password = "0000"
            )
        ),
        onAddCardClick = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListScreenPreview_Many() {
    CardListScreen(
        cardListUiState = CardListUiState.Many(
            cards = listOf(
                Card(
                    number = "0000 - 0000 - 0000 - 0000",
                    expiredDate = "00 / 00",
                    ownerName = "홍길동",
                    password = "0000"
                ),
                Card(
                    number = "0000 - 0000 - 0000 - 0001",
                    expiredDate = "00 / 00",
                    ownerName = "홍길동",
                    password = "0000"
                )
            )
        ),
        onAddCardClick = {}
    )
}
