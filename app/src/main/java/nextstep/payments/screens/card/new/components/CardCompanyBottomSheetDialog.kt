package nextstep.payments.screens.card.new.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.payments.screens.card.CardCompanyState
import nextstep.payments.ui.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardCompanyBottomSheetDialog(
    onDismissRequest: () -> Unit,
    onCardCompanyClick: (CardCompanyState) -> Unit,
    modifier: Modifier = Modifier,
) {
    val modalBottomSheetState = rememberModalBottomSheetState(
        confirmValueChange = { false }
    )
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        sheetState = modalBottomSheetState,
        onDismissRequest = onDismissRequest,
        properties = ModalBottomSheetProperties(
            shouldDismissOnBackPress = false
        ),
        dragHandle = null,
        modifier = modifier,
    ) {
        Spacer(Modifier.height(80.dp))
        CardCompanySelectRow(
            onCardCompanyClick = {
                onCardCompanyClick(it)
                scope.launch {
                    modalBottomSheetState.hide()
                }.invokeOnCompletion {
                    onDismissRequest()
                }
            },
            modifier = Modifier.padding(horizontal = 16.dp),
        )
        Spacer(Modifier.height(80.dp))
    }
}

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun CardCompanySelectRow(
    onCardCompanyClick: (CardCompanyState) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        CardCompanyState.entries.forEach { cardCompanyState ->
            CardCompaniesItem(
                cardCompany = cardCompanyState,
                onClick = { onCardCompanyClick(cardCompanyState) },
                modifier = Modifier.width(80.dp),
            )
        }
    }
}

@Composable
private fun CardCompaniesItem(
    cardCompany: CardCompanyState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable(onClick = onClick),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(cardCompany.imageRes),
            contentDescription = stringResource(cardCompany.nameRes),
            modifier = Modifier.size(36.dp)
        )
        Text(text = stringResource(cardCompany.nameRes))
    }
}

@Preview
@Composable
private fun CardCompanyBottomSheetDialogPreview() {
    PaymentsTheme {
        CardCompanyBottomSheetDialog(
            onCardCompanyClick = {},
            onDismissRequest = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCompanySelectRowPreview() {
    PaymentsTheme {
        CardCompanySelectRow(
            onCardCompanyClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CardCompanyItemPreview() {
    PaymentsTheme {
        CardCompaniesItem(
            cardCompany = CardCompanyState.KB,
            onClick = {},
        )
    }
}
