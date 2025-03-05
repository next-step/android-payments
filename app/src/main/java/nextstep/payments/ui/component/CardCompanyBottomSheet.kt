package nextstep.payments.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.payments.data.model.CardCompany

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardSelectSheet(
    selectedCompany: CardCompany?,
    onCardCompanyChange: (CardCompany) -> Unit,
    modifier: Modifier = Modifier,
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(36.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        CardCompany.entries.forEach {
            CardCompany(
                company = it,
                selected = selectedCompany == it,
                onSelect = onCardCompanyChange,
            )
        }
    }
}

private const val COLUMN_COUNT = 4
private const val ROW_COUNT = 2

@Preview(showBackground = true)
@Composable
private fun PreviewCardSelectSheet() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter,
    ) {
        CardSelectSheet(
            selectedCompany = null,
            onCardCompanyChange = {},
        )
    }
}