package nextstep.payments.ui.card.list

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.payments.ui.card.newcard.NewCardActivity
import nextstep.payments.ui.theme.PaymentsTheme

class CardListActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaymentsTheme {
                CardListScreen(onShowNewCard = {
                    startActivity(
                        Intent(
                            this,
                            NewCardActivity::class.java,
                        )
                    )
                })
            }
        }
    }

}
