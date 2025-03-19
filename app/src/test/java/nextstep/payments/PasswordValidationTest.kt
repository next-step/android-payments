package nextstep.payments

import nextstep.payments.ui.newcard.NewCardInputValidator
import org.junit.Assert
import org.junit.Test

class PasswordValidationTest {

    @Test
    fun 패스워드는_8에서_16자여야_한다() {
        val password = "password12#"
        Assert.assertTrue(NewCardInputValidator.validatePassword(password))
    }

    @Test
    fun 패드웨드가_8자_미만이면_에러가_반한된다() {
        val password = "1234"
        Assert.assertTrue(NewCardInputValidator.validatePassword(password) == false)
    }

    @Test
    fun 패드웨드가_16자_초과이면_에러가_반한된다() {
        val password = "123456789012345678"
        Assert.assertTrue(NewCardInputValidator.validatePassword(password) == false)
    }

    @Test
    fun 패스워드는_영문과_숫자를_포함해야_한다() {
        val password = "password12"
        Assert.assertTrue(NewCardInputValidator.validatePassword(password))
    }

    @Test
    fun 패스워드가_영문을_포함하지_않으면_에러가_반한된다() {
        val password = "!@#$%^&12"
        Assert.assertTrue(NewCardInputValidator.validatePassword(password) == false)
    }

    @Test
    fun 패스워드가_숫자를_포함하지_않으면_에러가_반한된다() {
        val password = "passwordpasd"
        Assert.assertTrue(NewCardInputValidator.validatePassword(password) == false)
    }
}