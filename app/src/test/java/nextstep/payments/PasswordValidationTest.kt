package nextstep.payments

import nextstep.payments.model.ValidationResult
import nextstep.payments.ui.newcard.NewCardInputValidator
import org.junit.Assert
import org.junit.Test

class PasswordValidationTest {

    @Test
    fun 패스워드는_4자여야_한다() {
        val password = "1234"
        Assert.assertTrue(NewCardInputValidator.validatePassword(password) == ValidationResult.SUCCESS)
    }

    @Test
    fun 패드웨드가_4자_미만이면_에러가_반한된다() {
        val password = "1"
        Assert.assertTrue(NewCardInputValidator.validatePassword(password) == ValidationResult.ADDITIONAL_INPUT_REQUIRED)
    }

    @Test
    fun 패드웨드가_8자_초과이면_에러가_반한된다() {
        val password = "123456789012345678"
        Assert.assertTrue(NewCardInputValidator.validatePassword(password) == ValidationResult.INPUT_REJECTED)
    }
}