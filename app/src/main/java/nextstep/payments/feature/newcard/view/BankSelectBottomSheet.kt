package nextstep.payments.feature.newcard.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nextstep.payments.model.BankType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    modalBottomSheetState: SheetState,
    onDismissRequest: () -> Unit,
    onBankSelected: (BankType) -> Unit = {}
) {
    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismissRequest,
        dragHandle = {
            Box(modifier = Modifier.height(24.dp))
        }
    ) {
        BankSelectRow(
            onClick = onBankSelected
        )
    }
}