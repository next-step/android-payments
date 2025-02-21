package nextstep.payments.ui

import androidx.compose.foundation.layout.Column
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.component.CardAdd
import nextstep.payments.ui.component.CardAddAffordance
import nextstep.payments.ui.component.CardListTopBar
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.utils.toNewCard
import nextstep.payments.viewmodel.CardListVIewModel

@Composable
fun CardListScreen(
    viewModel: CardListVIewModel = viewModel()
) {
    val cards by viewModel.cards.collectAsStateWithLifecycle()
    val showAddButton by remember(cards) { mutableStateOf(cards.size > 1) }
    val context = LocalContext.current

    Scaffold(
        topBar = {
            CardListTopBar { context.toNewCard() }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                if (cards.isEmpty()) {
                    item { CardAddAffordance() }
                }
                items(
                    items = cards,
                    key = { it.number }
                ) { card ->
                    PaymentCard()
                }

                if (cards.size < 2) {
                    item { CardAdd() }
                }
            }
        }
    }
}