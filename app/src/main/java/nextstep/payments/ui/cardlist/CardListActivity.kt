package nextstep.payments.ui.cardlist

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import nextstep.payments.R
import nextstep.payments.ui.editcard.EditCardRoute
import nextstep.payments.ui.newcard.NewCardActivity
import nextstep.payments.ui.theme.PaymentsTheme

class CardListActivity : ComponentActivity() {
    private val viewModel: CardListViewModel by viewModels()

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()
            val context = LocalContext.current
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()
            val launcher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it.resultCode == RESULT_OK) {
                        viewModel.fetchCards()
                    }
                }
            val editCardLauncher =
                rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                    if (it.resultCode == RESULT_OK) {
                        viewModel.fetchCards()
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(message = context.getString(R.string.card_edit_success))
                        }
                    }
                }

            LaunchedEffect(true) {
                viewModel.errorFlow.collectLatest {
                    snackbarHostState.showSnackbar(message = context.getString(R.string.credit_card_error))
                }
            }

            LaunchedEffect(true) {
                viewModel.editCard.collectLatest { creditCard ->
                    EditCardRoute.startActivity(
                        targetCard = creditCard,
                        context = this@CardListActivity,
                        launcher = editCardLauncher,
                    )
                }
            }

            PaymentsTheme {
                Scaffold(
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
                ) { innerPadding ->
                    CardListScreen(
                        modifier = Modifier.padding(innerPadding),
                        uiState = uiState,
                        onAddCardClick = {
                            val intent = Intent(this, NewCardActivity::class.java)
                            launcher.launch(intent)
                        },
                        onCardClick = viewModel::editCard,
                    )
                }
            }
        }
    }
}