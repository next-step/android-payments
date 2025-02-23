package nextstep.payments.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import nextstep.payments.R
import nextstep.payments.data.model.Card
import nextstep.payments.data.model.cardCompanyList
import nextstep.payments.ui.theme.PaymentsTheme
import nextstep.payments.ui.theme.Typography

@Composable
fun Card(model: Card) {

    val cardColor = model.company?.color ?: Color(0xFF333333)
    val textColor = cardColor.getTextColorForBackground()

    Card(backgroundColor = cardColor) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 15.dp)
                .align(Alignment.BottomCenter),
        )
        {
            Text(
                text = model.company?.name.orEmpty(),
                style = Typography.bodySmall,
                color = textColor,
            )
            Box(
                modifier = Modifier
                    .padding(top = 15.dp, bottom = 8.dp)
                    .size(width = 40.dp, height = 26.dp)
                    .background(
                        color = Color(0xFFCBBA64),
                        shape = RoundedCornerShape(4.dp),
                    ),
            )
            Text(
                text = model.number,
                style = Typography.bodySmall,
                color = textColor,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = model.ownerName,
                    style = Typography.bodySmall,
                    color = textColor,
                )
                Text(
                    text = model.expiredDate,
                    style = Typography.bodySmall,
                    color = textColor,
                )
            }
        }

        model.company?.let {company ->
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(company.imageUrl)
                    .build(),
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .clip(CircleShape)
                    .size(24.dp),
                placeholder = painterResource(R.drawable.loading_img),
                contentDescription = stringResource(R.string.company_image, company.name),
            )
        }
    }
}

private fun String.toCardNumberFormat(): String {
    return ""
}

@Composable
fun Card(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color(0xFFE5E5E5),
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .shadow(8.dp)
            .width(208.dp)
            .heightIn(min = 124.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(5.dp),
            ),
    ) {
        content()
    }
}

@Preview
@Composable
private fun PaymentCardPreview() {
    PaymentsTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Card(
                model = Card(
                    number = "1111 - 2222 - **** - ****",
                    ownerName = "CREW",
                    password = "Rebbecca",
                    expiredDate = "04 / 21",
                    company = cardCompanyList.getOrNull(0),
                )
            )

            Card(
                model = Card(
                    number = "1111 - 2222 - **** - ****",
                    ownerName = "CREW",
                    password = "Rebbecca",
                    expiredDate = "04 / 21",
                    company = cardCompanyList.getOrNull(1),
                )
            )

            Card(
                model = Card(
                    number = "1111 - 2222 - **** - ****",
                    ownerName = "CREW",
                    password = "Rebbecca",
                    expiredDate = "04 / 21",
                    company = cardCompanyList.getOrNull(2),
                )
            )
        }
    }
}

private fun Color.getTextColorForBackground(): Color {
    val luminance = (0.299 * this.red + 0.587 * this.green + 0.114 * this.blue)
    return if (luminance > 0.5) Color.Black else Color.White
}