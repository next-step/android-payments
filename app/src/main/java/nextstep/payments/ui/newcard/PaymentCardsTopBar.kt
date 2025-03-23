package nextstep.payments.ui.newcard

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nextstep.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentCardsTopBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(R.string.payment_cards_title)) },
        modifier = modifier
    )
}