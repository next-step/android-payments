package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.model.BankType

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectRow(
    items: List<BankType>,
    onItemClick: (BankType) -> Unit,
    modifier: Modifier = Modifier
) {

    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        items.forEach {
            BankSelectItem(
                item = it,
                onItemClick = onItemClick,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectRowPreview() {
    PaymentsTheme {
        BankSelectRow(
            items = BankType.getSelectBanks(),
            onItemClick = {}
        )
    }
}