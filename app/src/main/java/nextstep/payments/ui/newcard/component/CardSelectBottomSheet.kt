package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.SheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.SecureFlagPolicy
import kotlinx.coroutines.launch
import nextstep.payments.model.CardCompany


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSelectBottomSheet(
    sheetState: SheetState,
    cardCompanies: List<CardCompany>,
    onCardClick: (CardCompany) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    ModalBottomSheet(
        containerColor = Color.White,
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        properties = ModalBottomSheetProperties(
            securePolicy = SecureFlagPolicy.Inherit,
            isFocusable = true,
            shouldDismissOnBackPress = false
        )
    ) {
        val coroutineScope = rememberCoroutineScope()
        Box(modifier = Modifier.padding(bottom = 70.dp)) {
            CardCompanySelectRow(
                modifier = modifier,
                cardCompanies = cardCompanies,
                onClickCardCompany = {
                    coroutineScope.launch {
                        onCardClick(it)
                        sheetState.hide()
                    }.invokeOnCompletion { onDismissRequest() }
                },
            )
        }
    }
}