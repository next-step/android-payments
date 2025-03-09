package nextstep.payments.screens.card.update

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import nextstep.payments.ui.theme.PaymentsTheme

class UpdateCardActivity : ComponentActivity() {
    private val cardId: Int by lazy { intent.getIntExtra(CARD_KEY, CARD_ERROR_KEY) }
    private val updateCardViewModel: UpdateCardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (cardId != CARD_ERROR_KEY) {
            updateCardViewModel.setEditCardMode(cardId)
        }

        collectCardUpdated()

        setContent {
            PaymentsTheme {
                UpdateCardScreen(
                    viewModel = updateCardViewModel,
                    onBackClick = { finish() },
                )
            }
        }
    }

    private fun collectCardUpdated() =
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                updateCardViewModel.cardUpdated.collectLatest { cardUpdated ->
                    if (cardUpdated) {
                        setResult(RESULT_OK)
                        finish()
                    }
                }
            }
        }


    companion object {
        private const val CARD_KEY = "card"
        private const val CARD_ERROR_KEY = -1

        fun getIntent(context: Context): Intent {
            return Intent(context, UpdateCardActivity::class.java)
        }

        fun getIntentForPutExtraCardId(context: Context, cardId: Int): Intent {
            return Intent(context, UpdateCardActivity::class.java).apply {
                putExtra(CARD_KEY, cardId)
            }
        }
    }
}
