package nextstep.payments.newcard.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.BankType
import nextstep.payments.newcard.NewCardEvent
import nextstep.payments.newcard.model.BankTypeUiModel
import nextstep.payments.ui.theme.PaymentsTheme

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun BankSelectBottomSheet(
    sendEvent: (NewCardEvent) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState()
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = { },
        modifier = modifier,
    ) {
        BankSelectBottomSheetContent(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 36.dp, horizontal = 47.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelectBottomSheetContent(
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT,
    ) {
        BankType.getBankTypes().forEach {
            BankItem(
                bankTypeUiModel = BankTypeUiModel.from(it),
                modifier = Modifier.defaultMinSize(minWidth = 70.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun BankSelectBottomSheetPreview() {
    PaymentsTheme {
        BankSelectBottomSheet(
            sendEvent = {}
        )
    }
}