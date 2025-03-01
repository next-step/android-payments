package nextstep.payments.list.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.list.CardListViewModel
import nextstep.payments.R
import nextstep.payments.common.component.EmptyCard
import nextstep.payments.list.component.CardListTopBar
import nextstep.payments.list.model.CardUiState

@Composable
fun CardListRoute(
    viewModel: CardListViewModel,
    moveToAddCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.value) {
        CardUiState.Empty -> CardsEmptyScreen(
            moveToAddCard = moveToAddCard,
            modifier = modifier
        )

        is CardUiState.One -> TODO() // TODO : 한개 경우
        is CardUiState.Many -> TODO() // TODO : 여러개 경우
    }
}

@Composable
fun CardsEmptyScreen(
    moveToAddCard: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CardListTopBar()
        },
        modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.add_new_card),
                fontWeight = FontWeight.W700,
                fontSize = 18.sp,
                lineHeight = 36.sp,
                letterSpacing = 0.5.sp,
            )
            Spacer(modifier = Modifier.height(32.dp))
            EmptyCard(
                modifier = Modifier.clickable { moveToAddCard() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CardEmptyScreenPreview() {
    CardsEmptyScreen({})
}
