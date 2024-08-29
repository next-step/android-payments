package nextstep.payments.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.BankType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    bankTypes: List<BankType>,
    onClickBankType: () -> Unit,
    onDismissRequest: () -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismissRequest,
        containerColor = Color.White
    ) {
        BankSelectRow(
            bankTypes = bankTypes,
            onClickBankType = onClickBankType
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun BankSelectRow(
    bankTypes: List<BankType>,
    onClickBankType: () -> Unit
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 42.dp, vertical = 36.dp),
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = 4
    ) {
        bankTypes.forEach { bankType ->
            BankSelectorItem(
                bankType = bankType,
                onClickBankType = onClickBankType,
                modifier = Modifier.weight(1f)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun BankSelectRowPreview() {
    BankSelectRow(
        bankTypes = BankType.entries,
        onClickBankType = {}
    )
}
