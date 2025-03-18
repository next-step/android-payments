package nextstep.payments.newcard.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.payments.newcard.model.CardCompany

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCardBottomSheet(
    onCardClick: (CardCompany) -> Unit,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )
    val coroutineScope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = {},
        sheetState = modalBottomSheetState,
        containerColor = Color.White,
        shape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
    ) {
        Box(
            modifier = Modifier
                .wrapContentHeight()
        ) {
            CardCompanyFlowRowList { company ->
                coroutineScope.launch {
                    modalBottomSheetState.hide()
                }.invokeOnCompletion {
                    onCardClick(company)
                }
            }
        }
    }
}
