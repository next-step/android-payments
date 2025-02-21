package nextstep.payments

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import nextstep.payments.ui.newcard.NewCardActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RouteActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun `카드추가_화면전환_이벤트가_발생시_카드추가_화면으로_이동해야_한다`() {
        //when
        composeTestRule.onNodeWithContentDescription("add_card_icon").performClick()

        //then
        intended(hasComponent(NewCardActivity::class.java.name))
    }
}