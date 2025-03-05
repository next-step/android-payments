package nextstep.payments.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
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
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.Typography

@Composable
fun CardCompany(
    company: CardCompany,
    onSelect: (CardCompany) -> Unit,
    selected: Boolean,
) {
    if (selected) {
        SelectedCardCompany(company = company, onSelect = onSelect)
    } else {
        UnselectedCardCompany(company = company, onSelect = onSelect)
    }
}

@Composable
private fun CardCompany(company: CardCompany, onSelect: (CardCompany) -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onSelect(company) },
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
            contentDescription = stringResource(R.string.company_image, company.displayName),
        )
        Text(
            text = company.displayName,
            style = Typography.bodyLarge,
        )
    }
}

@Composable
private fun SelectedCardCompany(company: CardCompany, onSelect: (CardCompany) -> Unit) {
    Box {
        CardCompany(company = company, onSelect = onSelect)
        Row(
            modifier = Modifier.offset(x = 32.dp, y = (-4).dp),
            horizontalArrangement = Arrangement.End
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = stringResource(R.string.card_company_selected)
            )
        }
    }
}

@Composable
private fun UnselectedCardCompany(company: CardCompany, onSelect: (CardCompany) -> Unit) {
    CardCompany(company = company, onSelect = onSelect)
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
private fun SelectCardCompanyPreview() {
    PaymentsTheme {
        SelectedCardCompany(
            CardCompany.BC,
            onSelect = {}
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
private fun UnselectCardCompanyPreview() {
    PaymentsTheme {
        UnselectedCardCompany(
            CardCompany.BC,
            onSelect = {}
        )
    }
}