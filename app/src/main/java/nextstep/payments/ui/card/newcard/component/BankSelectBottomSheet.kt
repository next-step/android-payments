package nextstep.payments.ui.card.newcard.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.payments.model.BankType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BankSelectBottomSheet(
    onClickBank: (BankType) -> Unit,
    onDismissRequest: () -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(confirmValueChange = { false })
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        containerColor = Color.White,
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismissRequest,
    ) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 36.dp),
        ) {
            BankSelector(
                onClickBank = {
                    coroutineScope.launch {
                        onClickBank(it)
                        modalBottomSheetState.hide()
                        onDismissRequest()
                    }
                },
            )
        }
    }
}
