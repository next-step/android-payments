package nextstep.payments.ui.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.ui.theme.TopBarTitleColor


@Composable
fun CreditCardScreen(
    navigateToCardList: () -> Unit,
    viewModel: CreditCardViewModel = viewModel()
) {
    val creditCardUiState by viewModel.creditCardUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CardListTopAppBar(
                uiState = creditCardUiState,
                onClickAddCard = navigateToCardList
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            CreditCardScreen(
                uiState = creditCardUiState,
                onClickAddItem = navigateToCardList
            )
        }
    }
}

@Composable
fun CreditCardScreen(
    uiState: CreditCardUiState,
    onClickAddItem: () -> Unit
) {
    when (uiState) {
        is CreditCardUiState.Empty -> EmptyCardScreen(
            onClickItem = onClickAddItem
        )
        is CreditCardUiState.One ->
            SingleCardScreen(
                card = uiState.card,
                onClickAddItem = onClickAddItem
            )
        is CreditCardUiState.Many -> CardListScreen(uiState.cards)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopAppBar(
    uiState: CreditCardUiState,
    onClickAddCard: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.payments),
                color = TopBarTitleColor,
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            if (uiState is CreditCardUiState.Many) {
                TextButton(
                    onClick = { onClickAddCard() }
                ) {
                    Text(
                        text = stringResource(id = R.string.add),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun CreditCardScreePreview() {
    CreditCardScreen(
        navigateToCardList = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListTopAppBarPreview() {
    val uiState = CreditCardUiState.Empty
    CardListTopAppBar(uiState, {})
}
