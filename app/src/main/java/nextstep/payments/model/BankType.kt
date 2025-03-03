package nextstep.payments.model

enum class BankType {
    NOT_SELECTED, KB, BC, WOORI, SHINHAN, KAKAOBANK, HYUNDAI, LOTTE, HANA;

    companion object {
        fun getBankTypes(): List<BankType> {
            return listOf(
                BC, SHINHAN, KAKAOBANK, HYUNDAI,
                WOORI, LOTTE, HANA, KB,
            )
        }
    }
}