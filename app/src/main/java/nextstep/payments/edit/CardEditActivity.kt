package nextstep.payments.edit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.ui.theme.PaymentsTheme

class CardEditActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val id = intent.getStringExtra(KEY_CARD_ID) ?: ""

        setContent {
            PaymentsTheme {
                CardEditScreen(
                    cardId = id,
                    navigateToList = {
                        finish()
                    },
                    navigateToListWithEdit = {
                        setResult(RESULT_OK)
                        finish()
                    }
                )
            }
        }
    }

    companion object {
        const val KEY_CARD_ID = "card_id"
    }
}