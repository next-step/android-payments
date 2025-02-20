package nextstep.payments.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.ui.component.CardTopBar
import nextstep.payments.ui.component.PaymentCard
import nextstep.payments.viewmodel.CardListVIewModel

@Composable
fun CardListScreen(
    vIewModel: CardListVIewModel = viewModel()
) {
    val cards by vIewModel.cards.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CardTopBar(
                title = stringResource(R.string.payments),
                onBackClick = { },
                onSaveClick = { },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            LazyColumn {
                items(
                    items = cards,
                    key = { it.number }
                ) { card ->
                    PaymentCard()
                }
            }
        }
    }
}