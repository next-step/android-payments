package nextstep.payments.card

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import nextstep.payments.card.add.AddingCardActivity

class CardsActivity : ComponentActivity() {
    private val viewModel: CardsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CardsScreen(
                cardsViewModel = viewModel,
                onAddCardClick = { launchAddCardActivity() },
                onCardClick = {}
            )
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.updateCards()
    }

    private fun launchAddCardActivity() {
        val intent = Intent(this, AddingCardActivity::class.java)
        startActivity(intent)
    }
}
