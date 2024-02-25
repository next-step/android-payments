package nextstep.payments.card.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardScreenTopBar(
    onAddButtonClick: () -> Unit,
    showAddButton: Boolean = false
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                textAlign = TextAlign.Center,
                text = stringResource(R.string.cards_screen_top_bar_title)
            )
        },
        actions = {
            if (showAddButton) {
                Text(
                    modifier = Modifier
                        .clickable(onClick = onAddButtonClick)
                        .padding(end = 20.dp),
                    text = stringResource(id = R.string.cards_screen_top_bar_add_button),
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    )
}

@Preview
@Composable
private fun AddingCardScreenTopBarPreview() {
    CardScreenTopBar(
        onAddButtonClick = {},
    )
}

@Preview
@Composable
private fun AddingCardScreenTopBarPreview_showAddButton() {
    CardScreenTopBar(
        onAddButtonClick = {},
        showAddButton = true,
    )
}
