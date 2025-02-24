package nextstep.payments.ui.newcard.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import nextstep.payments.designsystem.theme.PaymentsTheme
import nextstep.payments.ext.setContentDescription
import nextstep.payments.model.BankType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    selectedBank: BankType,
    modifier: Modifier = Modifier,
    onItemClick: (BankType) -> Unit
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = selectedBank) {
        if (selectedBank != BankType.NOT_SELECTED) {
            modalBottomSheetState.hide()
        }
    }

    if (selectedBank == BankType.NOT_SELECTED) {
        ModalBottomSheet(
            modifier = modifier.setContentDescription("BankSelectBottomSheet"),
            sheetState = modalBottomSheetState,
            onDismissRequest = {
                scope.launch {
                    modalBottomSheetState.hide()
                }
            },
        ) {
            BankSelectRow(
                items = BankType.getSelectBanks(),
                onItemClick = { item -> onItemClick(item) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectBottomSheetPreview1() {
    PaymentsTheme {
        BankSelectBottomSheet(
            selectedBank = BankType.NOT_SELECTED,
            onItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectBottomSheetPreview2() {
    PaymentsTheme {
        BankSelectBottomSheet(
            selectedBank = BankType.KB,
            onItemClick = {}
        )
    }
}