package nextstep.payments.ui.card.list

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.payments.ui.card.newcard.NewCardActivity

@Composable
fun CardListScreen(viewModel: CardListViewModel = viewModel()) {
    val context = LocalContext.current
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                viewModel.fetchCards()
            }
        }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (uiState) {
        is CardListUiState.Empty -> Text(text = "empty", Modifier.clickable { launcher.launch(Intent(context, NewCardActivity::class.java)) })
        is CardListUiState.One -> Text(text = "one", Modifier.clickable { launcher.launch(Intent(context, NewCardActivity::class.java)) })
        is CardListUiState.Many -> Text(text = "many", Modifier.clickable { launcher.launch(Intent(context, NewCardActivity::class.java)) })
    }
}

@Preview
@Composable
private fun CardListScreenPreview() {
    CardListScreen(viewModel = CardListViewModel())
}
