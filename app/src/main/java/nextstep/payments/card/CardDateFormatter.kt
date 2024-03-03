package nextstep.payments.card

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

interface CardDateFormatter {
    fun toDate(source: String): Date?

    fun toFormatString(date: Date): String
}

object CardExpireDateFormatter : CardDateFormatter {
    private val dateFormat = SimpleDateFormat("mm/yy", Locale.KOREA)

    override fun toDate(source: String): Date? {
        return dateFormat.parse(source)
    }

    override fun toFormatString(date: Date): String {
        return dateFormat.format(date)
    }
}
