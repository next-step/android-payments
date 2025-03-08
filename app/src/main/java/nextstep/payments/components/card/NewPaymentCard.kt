package nextstep.payments.components.card

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.components.card.elements.CardCompanyName
import nextstep.payments.components.card.elements.IcChip
import nextstep.payments.screens.card.uistate.CardCompanyUiState
import nextstep.payments.ui.theme.PaymentsTheme

@Composable
fun NewPaymentCard(
    cardCompanyUiState: CardCompanyUiState,
    modifier: Modifier = Modifier,
) {
    BaseCard(
        color = cardCompanyUiState.backgroundColor,
        modifier = modifier,
    ) {
        CardCompanyName(stringResource(cardCompanyUiState.nameRes))
        Spacer(Modifier.height(16.dp))
        IcChip()
        Spacer(Modifier.height(8.dp))
        Spacer(Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun NewPaymentCardPreview() {
    PaymentsTheme {
        NewPaymentCard(
            cardCompanyUiState = CardCompanyUiState.BC,
        )
    }
}
