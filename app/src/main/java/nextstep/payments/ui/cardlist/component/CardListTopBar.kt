package nextstep.payments.ui.cardlist.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import nextstep.payments.R
import nextstep.payments.designsystem.theme.PaymentsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListTopBar(
    onAddClick: () -> Unit,
    modifier: Modifier = Modifier,
    isShowAddText: Boolean = true
) {
    CenterAlignedTopAppBar(
        title = {
            Text(stringResource(R.string.top_app_bar_card_list))
        },
        actions = {
            if(isShowAddText){
                TextButton(onClick = onAddClick) {
                    Text(
                        text = stringResource(R.string.add),
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun CardListTopBarPreview() {
    PaymentsTheme {
        CardListTopBar(
            onAddClick = {}
        )
    }
}