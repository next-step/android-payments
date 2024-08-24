package nextstep.payments.ui.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import nextstep.payments.data.Bank
import nextstep.payments.data.BankType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    banks: List<Bank>,
    onDismiss: (Bank) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )
    var selectedBank by remember {
        mutableStateOf(Bank())
    }
    LaunchedEffect(key1 = selectedBank) {
        if (selectedBank.bankType != BankType.NOT_SELECTED) {
            onDismiss(selectedBank)
//            modalBottomSheetState.hide()
        }
    }
    
    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = {}
    ) {
        BankSelectRow(banks, onClick = {
            selectedBank = it
        })
    }
}
