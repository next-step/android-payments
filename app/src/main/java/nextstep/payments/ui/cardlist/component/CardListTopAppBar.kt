package nextstep.payments.ui.cardlist.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.ui.theme.RobotoBold
import nextstep.payments.ui.theme.RobotoRegular

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopAppBar(
    onAddCardClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.card_list_top_app_bar_title),
                fontFamily = RobotoRegular,
                fontSize = 22.sp,
                color = Color.Black,
            )
        },
        actions = {
            Text(
                text = stringResource(R.string.card_list_top_app_bar_button),
                fontFamily = RobotoBold,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier
                    .clickable { onAddCardClick() }
                    .padding(all = 20.dp),
            )
        },
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListTopBarPreview() {
    CardListTopAppBar(
        onAddCardClick = { },
    )
}
