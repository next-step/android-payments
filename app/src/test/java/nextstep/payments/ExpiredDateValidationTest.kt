package nextstep.payments

import nextstep.payments.ui.newcard.NewCardInputValidator
import org.junit.Assert
import org.junit.Test

class ExpiredDateValidationTest {

    @Test
    fun 만료일은_숫자로만_이루어져야_한다() {
        val expiredDate = "1225"
        Assert.assertTrue(NewCardInputValidator.validateExpiredDate(expiredDate))
    }

    @Test
    fun 만료일에_숫자_이외의_문자가_있으면_에러가_반환된다() {
        val expiredDate = "12/2"
        Assert.assertTrue(NewCardInputValidator.validateExpiredDate(expiredDate) == false)
    }

    @Test
    fun 만료일은_4자리여야_한다() {
        val expiredDate = "1225"
        Assert.assertTrue(NewCardInputValidator.validateExpiredDate(expiredDate))
    }

    @Test
    fun 만료일이_4자리가_아니면_에러를_반환한다() {
        var expiredDate = "12"
        // 4자리 보다 짧은 경우
        Assert.assertTrue(NewCardInputValidator.validateExpiredDate(expiredDate) == false)

        // 4자리보다 긴 경우
        expiredDate = "123456"
        Assert.assertTrue(NewCardInputValidator.validateExpiredDate(expiredDate) == false)
    }
}