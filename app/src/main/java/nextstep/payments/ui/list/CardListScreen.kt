package nextstep.payments.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.R
import nextstep.payments.viewmodel.NewCardViewModel

@Composable
internal fun CardListScreen(
    modifier: Modifier = Modifier,
    viewModel: NewCardViewModel = viewModel(),
) {
    CardListScreen(

        modifier = modifier,
    )
}

@Composable
internal fun CardListScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { TopBar() },
        modifier = modifier
    ) { innerPadding ->
        CardListContent(modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF1D1B20)
            )
        },
        modifier = modifier,
    )
}

@Composable
private fun CardListContent(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
private fun StatefulCardListScreenPreview() {
    CardListScreen()
}
