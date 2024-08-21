package nextstep.payments.ui.creditcard

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.ui.component.PaymentsTopBar
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
internal fun CreditCardRoute(
    modifier: Modifier = Modifier,
    viewModel: CreditCardViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()

    CreditCardScreen(
        uiState = uiState,
        onAddCardClick = { TODO() },
        modifier = modifier,
    )
}

@Composable
internal fun CreditCardScreen(
    uiState: CreditCardUiState,
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            PaymentsTopBar(
                title = stringResource(id = R.string.title_credit_card),
                onBackClick = { TODO() },
                onActionClick =
                    if (uiState is CreditCardUiState.Many) {
                        { TODO() }
                    } else {
                        null
                    },
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        when (uiState) {
            is CreditCardUiState.Empty -> {
                EmptyCreditCardContent(
                    onAddCardClick = onAddCardClick,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(top = 32.dp, start = 73.dp, end = 73.dp),
                )
            }

            is CreditCardUiState.One -> TODO()
            is CreditCardUiState.Many -> TODO()
        }
    }
}

@Composable
private fun EmptyCreditCardContent(
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Text(
            text = stringResource(id = R.string.cretdit_card_empty_title),
            style = MaterialTheme.typography.titleMedium,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(32.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier =
                Modifier
                    .size(width = 208.dp, height = 124.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
                    .background(Color(0xFFE5E5E5))
                    .clickable(onClick = onAddCardClick),
        ) {
            Text(
                text = "+",
                fontSize = 32.sp,
                modifier = Modifier.size(width = 20.dp, height = 40.dp),
            )
        }
    }
}

@Preview
@Composable
private fun CreditCardScreenPreview() {
    PaymentsTheme {
        CreditCardScreen(
            uiState = CreditCardUiState.Empty,
            onAddCardClick = {},
        )
    }
}
