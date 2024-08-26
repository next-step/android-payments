package nextstep.payments.ui.card.list

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.model.Card
import nextstep.payments.ui.card.list.component.CardListTopBar
import nextstep.payments.ui.card.list.component.NewCard
import nextstep.payments.ui.card.newcard.NewCardActivity
import nextstep.payments.ui.component.PaymentCard

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
    )
}

@Composable
private fun CardListScreen(
    modifier: Modifier = Modifier,
    state: CardListUiState,
    onShowNewCard: () -> Unit,
) {
    Scaffold(
        topBar = {
            CardListTopBar(
                displayAdd = state is CardListUiState.Many,
                onShowNewCard = onShowNewCard,
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
                is CardListUiState.Empty -> CardListEmptyScreen(
                    onShowNewCard = onShowNewCard,
                    modifier = modifier,
                )

                is CardListUiState.One -> CardListOneScreen(
                    state = state,
                    onShowNewCard = onShowNewCard,
                    modifier = modifier,
                )

                is CardListUiState.Many -> CardListManyScreen(state = state, modifier = modifier)
            }
        }
    }
}

@Composable
private fun CardListEmptyScreen(
    modifier: Modifier = Modifier,
    onShowNewCard: () -> Unit,
) {
    Column(modifier = modifier.padding(top = 32.dp)) {
        Text(
            text = stringResource(id = R.string.register_new_card),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        NewCard(
            modifier = Modifier
                .padding(top = 32.dp)
                .width(208.dp)
                .height(124.dp),
            onClick = onShowNewCard,
        )
    }
}

@Composable
private fun CardListOneScreen(
    modifier: Modifier = Modifier,
    state: CardListUiState.One,
    onShowNewCard: () -> Unit,
) {
    Column(modifier = modifier.padding(top = 12.dp)) {
        PaymentCard(card = state.card)
        NewCard(
            modifier = Modifier
                .padding(top = 32.dp)
                .width(208.dp)
                .height(124.dp),
            onClick = onShowNewCard,
        )
    }
}

@Composable
private fun CardListManyScreen(
    modifier: Modifier = Modifier,
    state: CardListUiState.Many,
) {
    Column(modifier = modifier) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            items(state.cards) {
                PaymentCard(card = it)
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 300)
@Composable
private fun CardListScreenPreview(@PreviewParameter(CardListScreenPreviewParameterProvider::class) param: CardListUiState) {
    CardListScreen(state = param, onShowNewCard = {})
}

private class CardListScreenPreviewParameterProvider : PreviewParameterProvider<CardListUiState> {
    val card1 = Card(
        cardNumber = "0000 - 0000 - 0000 - 0000",
        expiredDate = "08/27",
        ownerName = "jay kang",
        password = "1234",
    )

    val card2 = Card(
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
