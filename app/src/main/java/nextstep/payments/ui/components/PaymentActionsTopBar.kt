package nextstep.payments.ui.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentActionsTopBar(
    title: String,
    onAddPaymentCard: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = { Text(text = title) },
        actions = {
            IconButton(onClick = { onAddPaymentCard() }) {
                Text(
                    text = stringResource(id = R.string.add_new_card),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        modifier = modifier
    )
}

@Preview
@Composable
private fun PaymentActionsTopBarPreview() {
    PaymentActionsTopBar(
        title = "Payments",
        onAddPaymentCard = { }
    )
}