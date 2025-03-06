package nextstep.payments.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.model.CardCompany
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanyBottomSheet(
    onCompanyClick: (CardCompany) -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(confirmValueChange = { false }),
) {
    ModalBottomSheet(
        modifier = modifier,
        sheetState = sheetState,
        onDismissRequest = {}
    ) {
        Box(
            modifier = Modifier.fillMaxWidth().height(228.dp)
        ) {
            CardCompanyFlowRow(
                modifier = Modifier.align(Alignment.Center),
                onCompanyClick = onCompanyClick
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun CardCompanyBottomSheetPreview() {
    PaymentsTheme {
        val sheetState = rememberModalBottomSheetState()

        CardCompanyBottomSheet(sheetState = sheetState, onCompanyClick = {})
    }
}