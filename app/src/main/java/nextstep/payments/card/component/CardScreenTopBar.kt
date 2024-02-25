package nextstep.payments.card.component

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.payments.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddingCardScreenTopBar() {
    TopAppBar(
        title = { Text(text = stringResource(R.string.cards_screen_top_bar_title)) },
    )
}

@Preview
@Composable
private fun AddingCardScreenTopBarPreview() {
    AddingCardScreenTopBar()
}
