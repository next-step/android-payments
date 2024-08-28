package nextstep.payments.ui.screen.editcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.payments.ui.theme.PaymentsTheme

class EditCardActivity : ComponentActivity() {

    private val viewModel: EditCardViewModel by viewModels<EditCardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cardId = intent.getStringExtra("CARD_ID")
        setContent {
            PaymentsTheme {
                if (cardId != null) {
                    val state by viewModel.state.collectAsStateWithLifecycle()

                    if (savedInstanceState == null) {
                        LaunchedEffect(Unit) {
                            viewModel.handleEvent(EditCardEvent.OnInit(cardId))
                        }
                    }

                    LaunchedEffect(state.saved) {
                        if (state.saved) {
                            setResult(RESULT_OK)
                            finish()
                        }
                    }

                    LaunchedEffect(state.backPressed) {
                        if (state.backPressed) {
                            finish()
                        }
                    }

                    EditCardRoute(
                        state = state,
                        eventSink = viewModel::handleEvent,
                    )
                } else {
                    finish()
                }
            }
        }
    }
}

