package nextstep.payments.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import nextstep.payments.enums.CardCompanyCategory

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardCompanySelector(
    onCardCompanySelect: (CardCompanyCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    val cardCompanyCategory by remember {
        mutableStateOf(CardCompanyCategory.entries)
    }
    FlowRow(
        modifier = modifier
            .padding(36.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(23.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        repeat(cardCompanyCategory.size) {
            CardCompanySelectorItem(
                modifier = Modifier.weight(1f),
                cardCompanyCategory = cardCompanyCategory[it],
                onCardCompanySelect = onCardCompanySelect
            )
        }
    }
}

@Composable
private fun CardCompanySelectorItem(
    cardCompanyCategory: CardCompanyCategory,
    onCardCompanySelect: (CardCompanyCategory) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.clickable { onCardCompanySelect(cardCompanyCategory) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier.size(37.dp), model = cardCompanyCategory.iconUrl, contentDescription = "${cardCompanyCategory.title} 아이콘"
        )
        Spacer(modifier = Modifier.height(9.dp))
        Text(
            text = cardCompanyCategory.title,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            color = Color(0xff525252)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BankSelectorPreview() {
    CardCompanySelector(onCardCompanySelect = {

    })
}
