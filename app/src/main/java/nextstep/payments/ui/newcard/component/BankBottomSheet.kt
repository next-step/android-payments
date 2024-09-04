package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.newcard.NewCardBankUiState
import nextstep.payments.ui.theme.PaymentsTheme

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BankBottomSheet(
    sheetState: SheetState,
    onBankClick: (NewCardBankUiState) -> Unit,
    onDismissRequest: () -> Unit = {},
) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
    ) {
        BottomSheetContent(
            onBankClick = onBankClick,
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BottomSheetContent(
    onBankClick: (NewCardBankUiState) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier.padding(horizontal = 36.dp, vertical = 48.dp),
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        NewCardBankUiState.entries.forEach {
            Surface(
                onClick = { onBankClick(it) },
                content = { BankIcon(bank = it) },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun BankBottomSheetPreview() {
    val sheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    PaymentsTheme {
        BankBottomSheet(sheetState = sheetState, onBankClick = {})
    }
}

@Preview
@Composable
private fun BottomSheetContentPreview() {
    PaymentsTheme {
        BottomSheetContent(
            onBankClick = {},
        )
    }
}