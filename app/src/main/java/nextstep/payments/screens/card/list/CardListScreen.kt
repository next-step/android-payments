package nextstep.payments.screens.card.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.screens.card.list.components.AddCard
import nextstep.payments.screens.card.list.components.CardListTopBar
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.Typography

@Composable
fun CardListScreen(
    modifier: Modifier = Modifier,
    viewModel: CardListViewModel = viewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    when (state) {
        CardListUiState.Empty -> CardListEmptyScreen(onAddCardClick = {}, modifier = modifier)
        is CardListUiState.Many -> {

        }

        is CardListUiState.One -> {

        }
    }
}

@Composable
fun CardListEmptyScreen(
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { CardListTopBar() },
        containerColor = Color.White,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.card_list_empty_please_add_your_new_card),
                style = Typography.titleMedium.copy(fontWeight = FontWeight.W700),
            )
            Spacer(Modifier.height(32.dp))
            AddCard(onAddCardClick = onAddCardClick)
        }
    }
}

@Preview(name = "카드 목록에 카드가 하나도 없을 때")
@Composable
private fun Preview1() {
    PaymentsTheme {
        CardListEmptyScreen(onAddCardClick = {})
    }
}
