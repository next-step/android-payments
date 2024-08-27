package nextstep.payments.ui.screen.editcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import nextstep.payments.ui.theme.PaymentsTheme

class EditCardActivity : ComponentActivity() {

    private val viewModel: EditCardViewModel by viewModels<EditCardViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cardId = intent.getStringExtra("CARD_ID")
        setContent {
            PaymentsTheme {
                if (cardId != null) {
                    LaunchedEffect(Unit) {
                        viewModel.handleEvent(EditCardEvent.OnInit(cardId))
                    }
                    EditCardRoute(
                        viewModel = viewModel,
                        eventSink = viewModel::handleEvent,
                        onBackPressed = { finish() },
                    )
                }
            }
        }
    }
}

