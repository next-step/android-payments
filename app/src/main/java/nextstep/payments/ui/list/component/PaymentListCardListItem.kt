package nextstep.payments.ui.list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import nextstep.payments.R
import nextstep.payments.ui.domain.model.Card
import java.time.format.DateTimeFormatter

@Composable
internal fun PaymentListCardListItem(
    card: Card,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.img_card_template),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
        )

        CompositionLocalProvider(
            LocalContentColor provides Color.White,
            LocalTextStyle provides MaterialTheme.typography.labelMedium
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = maskCardNumber(card.cardNumber))
                Box(modifier = Modifier.fillMaxWidth()) {
                    if (card.ownerName != null) {
                        Text(
                            text = card.ownerName,
                            modifier = Modifier.align(Alignment.CenterStart),
                        )
                    }
                    val formatter = DateTimeFormatter.ofPattern("MM / yy")
                    Text(
                        text = formatter.format(card.expiredDate),
                        modifier = Modifier.align(Alignment.CenterEnd)
                    )
                }
            }
        }
    }
}

private fun maskCardNumber(cardNumber: String): String = buildString {
    cardNumber.forEachIndexed { index, c ->
        if (index > 0 && index % 4 == 0) {
            append(" - ")
        }
        if (index > 7) {
            append("*")
        } else {
            append(c)
        }
    }
}
