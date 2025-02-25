package nextstep.payments.ui.updatecard

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import nextstep.payments.base.BaseComponentActivity
import nextstep.payments.model.Card
import nextstep.payments.util.JsonConfig

class UpdateCardActivity : BaseComponentActivity() {

    private val updateCardViewModel by lazy {
        ViewModelProvider(
            owner = this,
            factory = UpdateCardViewModel.createViewModelFactory()
        )[UpdateCardViewModel::class]
    }

    @Composable
    override fun SetContent() {
        UpdateCardScreen(
            onBackClick = { finish() },
            onUpdate = {
                setResult(RESULT_OK)
                finish()
            },
            viewModel = updateCardViewModel
        )
    }

    companion object {
        const val KEY_CARD_ITEM = "key_card_item"

        fun newInstance(context: Context, item: Card): Intent {
            return Intent(context, UpdateCardActivity::class.java).apply {
                putExtra(KEY_CARD_ITEM, JsonConfig.json.encodeToString(item))
            }
        }
    }
}