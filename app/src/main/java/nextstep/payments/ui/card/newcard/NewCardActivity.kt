package nextstep.payments.ui.card.newcard

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.ui.theme.PaymentsTheme

class NewCardActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                NewCardScreen(
                    backToCardList = {
                        setResult(RESULT_OK)
                        finish()
                    },
                    showToast = {
                        Toast
                            .makeText(this, it, Toast.LENGTH_SHORT)
                            .show()
                    },
                )
            }
        }
    }

    companion object {
        const val EXTRA_CARD = "EXTRA_CARD"
    }

}
