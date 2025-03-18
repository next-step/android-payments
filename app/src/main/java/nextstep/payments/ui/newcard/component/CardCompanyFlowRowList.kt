package nextstep.payments.ui.newcard.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import nextstep.payments.R
import nextstep.payments.ui.newcard.model.CardCompany
import nextstep.payments.ui.theme.PaymentsTheme

private const val COLUMN_COUNT = 4

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CardCompanyFlowRowList(onCardClick: (CardCompany) -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(36.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.spacedBy(24.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        cardCompanyList.forEach { company ->
            Column(
                modifier = Modifier
                    .clickable { onCardClick(company) },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(company.imageId)
                        .build(),
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(37.dp),
                    contentDescription = "회사이름",
                )
                Spacer(modifier = Modifier.size(9.dp))
                Text(text = company.name)
            }
        }
    }
}

private val cardCompanyList = listOf(
    CardCompany(R.drawable.ic_bc, "BC카드"),
    CardCompany(R.drawable.ic_shinhan, "신한카드"),
    CardCompany(R.drawable.ic_kakao, "카카오뱅크"),
    CardCompany(R.drawable.ic_hyundae, "현대카드"),
    CardCompany(R.drawable.ic_woori, "우리카드"),
    CardCompany(R.drawable.ic_lotte, "롯데카드"),
    CardCompany(R.drawable.ic_hana, "하나카드"),
    CardCompany(R.drawable.ic_kb, "국민카드")
)

@Preview(showBackground = true)
@Composable
private fun CardCompanyFlowRowListPreview() {
    PaymentsTheme {
        CardCompanyFlowRowList({})
    }
}
