package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.component.card.CardBankInformation
import nextstep.payments.ui.theme.PaymentsTheme

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun BankBottomSheetContent(
    onBankClick: (CardBankInformation) -> Unit,
    modifier: Modifier = Modifier,
) {
    val banks = remember {
        CardBankInformation.entries.filterNot { it == CardBankInformation.None }
    }
    FlowRow(
        modifier = modifier.padding(horizontal = 36.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        key(banks) {
            banks.forEach { bank ->
                Surface(
                    onClick = { onBankClick(bank) },
                    content = { BankIcon(bank = bank) },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun BottomSheetContentPreview() {
    PaymentsTheme {
        BankBottomSheetContent(
            onBankClick = {},
        )
    }
}