package nextstep.payments.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.compose.currentStateAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.component.Card
import nextstep.payments.ui.component.CardAdd
import nextstep.payments.ui.component.CardAddAffordance
import nextstep.payments.ui.component.CardListTopBar
import nextstep.payments.utils.toCardAdd
import nextstep.payments.viewmodel.CardListViewModel
import nextstep.payments.viewmodel.CardsUiState

@Composable
fun CardListScreen(
    viewModel: CardListViewModel = viewModel()
) {

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val currentLifecycleState by lifecycleOwner.lifecycle.currentStateAsState()

    if (currentLifecycleState == Lifecycle.State.RESUMED) viewModel.fetchCards()

    val cardsUiState by viewModel.cardsUiState.collectAsStateWithLifecycle()
    val showAddButton by remember(cardsUiState) { mutableStateOf(cardsUiState is CardsUiState.Many) }

    Scaffold(
        topBar = {
            CardListTopBar(
                onAddClick = { context.toCardAdd() }.takeIf { showAddButton }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(32.dp),
                contentPadding = PaddingValues(top = 32.dp)
            ) {

                when (val uiState = cardsUiState) {
                    is CardsUiState.Empty -> {
                        item { CardAddAffordance() }
                        item { CardAdd() }
                    }

                    is CardsUiState.One -> {
                        item { Card(uiState.card) }
                        item { CardAdd() }
                    }

                    is CardsUiState.Many -> {
                        items(
                            items = uiState.cards,
                            key = { it -> it.number }
                        ) { card ->
                            Card(card)
                        }
                    }
                }
            }
        }
    }
}