package nextstep.payments.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import nextstep.payments.R
import nextstep.payments.data.model.CardCompany
import nextstep.payments.data.model.cardCompanyList
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.Typography

@Composable
fun CardCompany(company: CardCompany, selected: Boolean) {
    if (selected) SelectedCardCompany(company) else SelectedCardCompany(company)
}

@Composable
private fun CardCompany(company: CardCompany) {
    Column(
        modifier = Modifier.clickable { },
        verticalArrangement = Arrangement.spacedBy(9.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(company.imageUrl)
                .build(),
            modifier = Modifier
                .clip(CircleShape)
                .size(37.dp),
            placeholder = painterResource(R.drawable.loading_img),
            contentDescription = stringResource(R.string.company_image, company.name),
        )
        Text(
            text = company.name,
            style = Typography.bodyLarge,
        )
    }
}

@Composable
private fun SelectedCardCompany(company: CardCompany) {
    Box {
        CardCompany(company)
        Row(
            modifier = Modifier.offset(x = 32.dp, y = (-4).dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = stringResource(R.string.selected)
            )
        }
    }
}

@Composable
private fun UnSelectedCardCompany(company: CardCompany) {
    CardCompany(company)
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
private fun CardCompanyPreview() {
    PaymentsTheme {
        FlowRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            SelectedCardCompany(cardCompanyList.first())
            UnSelectedCardCompany(cardCompanyList.first())
        }
    }
}