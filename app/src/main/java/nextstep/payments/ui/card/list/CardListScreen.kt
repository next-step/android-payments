package nextstep.payments.ui.card.list

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.core.os.bundleOf
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.model.BankType
import nextstep.payments.model.Card
import nextstep.payments.ui.card.list.component.CardListTopBar
import nextstep.payments.ui.card.newcard.NewCardActivity
import nextstep.payments.ui.card.newcard.NewCardActivity.Companion.EXTRA_CARD
import nextstep.payments.ui.card.newcard.component.EmptyCardScreen
import nextstep.payments.ui.card.newcard.component.ManyCardScreen
import nextstep.payments.ui.card.newcard.component.OneCardScreen

@Composable
fun CardListScreen(viewModel: CardListViewModel = viewModel()) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                viewModel.fetchCards()
            }
        }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    CardListScreen(
        state = uiState,
        onShowNewCard = { launcher.launch(Intent(context, NewCardActivity::class.java)) },
        onEditCard = {
            launcher.launch(Intent(context, NewCardActivity::class.java).apply {
                putExtras(bundleOf(EXTRA_CARD to it))
            })
        },
    )
}

@Composable
private fun CardListScreen(
    modifier: Modifier = Modifier,
    state: CardListUiState,
    onShowNewCard: () -> Unit,
    onEditCard: (Card) -> Unit,
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                displayAdd = state is CardListUiState.Many,
                onShowNewCard = { onShowNewCard() },
            )
        },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.TopCenter,
        ) {
            when (val state = state) {
                is CardListUiState.Empty -> EmptyCardScreen(
                    onShowNewCard = { onShowNewCard() },
                    modifier = modifier,
                )

                is CardListUiState.One -> OneCardScreen(
                    state = state,
                    onShowNewCard = onShowNewCard,
                    onEditCard = onEditCard,
                    modifier = modifier,
                )

                is CardListUiState.Many -> ManyCardScreen(
                    state = state,
                    onEditCard = onEditCard,
                    modifier = modifier,
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
private fun CardListScreenPreview(@PreviewParameter(CardListScreenPreviewParameterProvider::class) param: CardListUiState) {
    CardListScreen(state = param, onShowNewCard = {}, onEditCard = {})
}

private class CardListScreenPreviewParameterProvider : PreviewParameterProvider<CardListUiState> {
    val card1 = Card(
        id = 1,
        bankType = BankType.BC,
        cardNumber = "0000 - 0000 - 0000 - 0000",
        expiredDate = "08/27",
        ownerName = "jay kang",
        password = "1234",
    )

    val card2 = Card(
        id = 2,
        bankType = BankType.KB,
        cardNumber = "0000 - 0000 - 0000 - 0000",
        expiredDate = "06/22",
        ownerName = "jihoi kang",
        password = "1234",
    )
    override val values: Sequence<CardListUiState> = sequenceOf(
        CardListUiState.Empty,
        CardListUiState.One(card1),
        CardListUiState.Many(listOf(card1, card2))
    )
}
