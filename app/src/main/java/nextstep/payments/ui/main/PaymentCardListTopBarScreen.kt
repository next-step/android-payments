package nextstep.payments.ui.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.state.CardUiState
import nextstep.payments.ui.theme.titleBoldStyle

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun PaymentCardListTopBar(
    actionContent: @Composable () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = "Payments",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        actions = {
            actionContent()
        },
        navigationIcon = {
            Box(modifier = Modifier.size(64.dp))
        },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
internal fun AddButton(
    cardUiState: CardUiState,
    onAddClick: () -> Unit
) {
    if (cardUiState is CardUiState.Many) {
        IconButton(
            onClick = onAddClick,
            modifier = Modifier.size(64.dp)
        ) {
            Text(
                text = "추가",
                style = titleBoldStyle
            )
        }
    } else {
        Box(modifier = Modifier.size(64.dp))
    }
}

@Preview
@Composable
private fun PaymentMainTopBarPreview() {
    PaymentCardListTopBar {
        AddButton(
            cardUiState = CardUiState.Empty,
            onAddClick = {}
        )
    }
}

@Preview
@Composable
private fun PaymentMainTopBarManyPreview() {
    PaymentCardListTopBar {
        AddButton(
            cardUiState = CardUiState.Many(listOf()),
            onAddClick = {}
        )
    }
}
