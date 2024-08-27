package nextstep.payments.ui.card.newcard.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import nextstep.payments.R
import nextstep.payments.model.BankType

private const val COLUMN_COUNT = 4
private const val IMAGE_SIZE = 37

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BankSelector(
    onClickBank: (BankType) -> Unit,
) {
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = COLUMN_COUNT
    ) {
        BankType.entries.forEach {
            Column(
                modifier = Modifier.clickable { onClickBank(it) },
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                AsyncImage(
                    model = ImageRequest
                        .Builder(LocalContext.current)
                        .data(it.logoUrl)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = "${it.name.lowercase()}Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(IMAGE_SIZE.dp),
                )

                Text(
                    text = stringResource(id = it.titleRes),
                    color = Color(0xFF525252),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 9.dp),
                )
            }
        }
    }
}

@Preview
@Composable
private fun BankSelectRowPreview() {
    BankSelector(onClickBank = {})
}
