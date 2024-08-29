package nextstep.payments.model

import androidx.compose.ui.graphics.Color
import nextstep.payments.ui.theme.BCColor
import nextstep.payments.ui.theme.HanaColor
import nextstep.payments.ui.theme.HyundaiColor
import nextstep.payments.ui.theme.KBColor
import nextstep.payments.ui.theme.KakaoColor
import nextstep.payments.ui.theme.LotteColor
import nextstep.payments.ui.theme.ShinhanColor
import nextstep.payments.ui.theme.WooriColor

enum class BankType(
    val companyName: String,
    val color: Color,
    val url: String?
) {
    BC(
        "BC카드",
        BCColor,
        "https://s3-alpha-sig.figma.com/img/ec2e/412b/5dbba0f6ed87192ed46b92e2e3aec83e?Expires=1725840000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=l7d-0XWxxcqdh~UmYkMjEa2hnHiBkSz2Elqmom1KOinW2DExeQCChh8zic9MWKgAlWuJIwbUWJGKfqMQp~XdP47X5OUSTVZTlrlgRVtGkq-6kxZL7OHrFZLGxfD5i4twUbEMoxnyeIBRwCA-oa~ynpKQ5Hf2eFfq~faBHWRf6oKZumGQf2kGWPrOB1qDfVJyX-aD5PiXVrH0SvslDCvaWxvUvCv9aLJACsunrDXtHm0PT3vSCwxSEt3YIhZy9fNYXRO7ISWUqKBxvxxiFXxTCdWZ1qd6qxJpD27cgXScUlcu-26S-YY53EM43C1B75Cs-PHAyMU5Sdj50n5pEF5oUQ__"
    ),
    SHINHAN(
        "신한카드",
        ShinhanColor,
        "https://s3-alpha-sig.figma.com/img/8cb5/ff9c/17af119128d7b34b76c54c1828dfcf98?Expires=1725840000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Na6f5SkBem-1UHvt8E47o0NM6E9a0hBeU07dlij7Fjqy5cBi2JmF1aWT1kVCyA2hqf4~Zf3uofuS4m8kJLhP9LEqy0EIu2mPXILkMCYWET9mW1~6GVcPHaKPgtKCmxGXi94mxs6RbxtZqiUIMFBolIyRFVZGixYGPcdTgP2vcoFft0ASAqxIBWKgYV1xoIgomfOXw3-vLzMwZ6qEuP1yNDXhwbMEE0odKQq4EQooj6Rr8MTDqXeSL9qX25Mmml8yhcBKkrA64je20y5jDzzmjAbigWCOGZ5i6fCYfKksc~WZOnONn-bLGNrLlh6zX2c25g4fNyREK9rYoylc-1vgdw__"
    ),
    KAKAO(
        "카카오뱅크",
        KakaoColor,
        "https://s3-alpha-sig.figma.com/img/62b3/d8f9/798f98848393b3bd58d726356ecfe1d4?Expires=1725840000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=O9vBuRygOSN60BHrMCTe8qSot6k5KgJMEQzSOANPmgFmy9IATar8Fz5IR892TGGgQ5rpKxxq64BJXIEXXah~boinxsFhWV8AKGLB~v0ZfjHCEiwMBAdu-v0jx9ReJtXiddd6XmPYx5AQjkwYlJM~VRV7AFyxrH2dj-6WraJ-852w1CIOeDeO0F4aif5axb5jEwdwlENU4Yc95rvtNO~Vl5~bbYz2Ti1dJYj17UiyMVITN0Xxl7ArxLl1m82f5AcRseclvSg~cb8ldK-RlB0MkcgH5i8btofwo19D8-EOQg8KNsjyQNhNJUi5JUNF4QN8bhQoKiQ0l2PwVTMY6vC~4A__"
    ),
    HYUNDAI(
        "현대카드",
        HyundaiColor,
        "https://s3-alpha-sig.figma.com/img/97bf/c00e/ce47fbe052835f468965626c191e75de?Expires=1725840000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=D64SRzbUwFVRkId-~ZHLoHqtyVexlI3JKodi2p0Jd1VMBLhHF~6L8jTgUZ3xmy9-I7ZquhsijyCxtdHfsW2LpCGNwa5zkUn9ivdS0uZb2pJGtqgVZr~lmmIXFC1w0gfLAUsHHEngDjnifUzs-nZ~PPzMjETbxlrS43byXSRuBO88x3lbcdnVIq8zQaVp0DJR5UnqnDXYnE~BYkLRbQaBMaJXj4dV-7xynrrz3pUMh5LO~g91i3DF0Wsi0zMjcZg8DBGfTXJp88NlctG4nrXgKCr0Z~GwQ1wfdxpLfebH36Ho1FjCJzOKS0-pnCfpQCg-Sfa4D~ZXaKmvbbF~j0wapA__"
    ),
    WOORI(
        "우리카드",
        WooriColor,
        "https://s3-alpha-sig.figma.com/img/14be/d1aa/29bb17713330074966c9944e0ee37e96?Expires=1725840000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=GBHXzINqqB3midLU-hrWtj0mHD8~AELgktH7egU~dpRkXlF0NDWqICqxbxpJoqRXBFqHcEAfZYfjrr54Yp-~jODwo3kTpleCtat0UNMSLO~RoENmo1GpuEnEpayo8FUPr7Te8EzsYjVIVZZ8SDu1LmCEWnY3lsiin0ftYD2HZaIVhXvJAIAT32gA8w78Mwj9~Xj6cpiyGwAkvii8HhDa5uWooP5V9QtaIU5EwNPYjrTHOwN98~Kv8Nbfbf1y1Ww2hUTXVBE5EErL4P0IXID5vwmUn6p0hAH7LJRAXYxaDQwzP9ZDxvDhiJUpCHehePfazBXX8sZF7dwU2-Vzjz8Wvg__"
    ),
    LOTTE(
        "롯데카드",
        LotteColor,
        "https://s3-alpha-sig.figma.com/img/c70a/5f12/c602b6ca83ab8e98b3f68ac17c4373e0?Expires=1725840000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=e3zZmow-P4A5gMw2WudY9CPHOEdAyT4uOp9aBeG5VeOCma2pfcHT3qkTJCc3ojghqaTV38kPe8QsVDi9JaOAiyBZXpR-JvkR6N32uZPRj79y92vuxPgso2CYNdmRL-RZpLnHJhiQdLoAyFnOlTjmcv2gDPgC90fbA-1uy0btjO2jAPvG~EhYKWJA~bi8UFonL9Bz65Wz5j0a~Vg77LzW2lZmAVEEIj0nDGpuzNpY3ozJaXRa8CvAq4zLFQ3jt807iiGUh5v~zvnU-yZQfz~ylYtR1OVvlpNmJ8TGK4dmbPvv6X00rMYwjDz-RqSK2waR9zFvL3x9EetP21auR5Gr7w__"
    ),
    HANA(
        "하나카드",
        HanaColor,
        "https://s3-alpha-sig.figma.com/img/001f/6745/31818ebbc2f3194a256a192eb0186f9e?Expires=1725840000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=SDhFrZJe618OqNXgOFnYjXIVHFWFqW-JiuamDqOnA8o6~ER2wXSuYmX3ZOXNfk4LGQSYpbbtiOdEpwlZZndfjKJkQmiXs6EIWQp9sw87OkE~1r8cd2C5pnMneQ8h60lJvx4NOohuVL4s7qRsKBzSH6DFADyNTCh5O2k~5tshLIakx-tWUxPoEG9mUen3k~j2QHANfAC1MH9YSEhtncryXIt3tmPs-lvh2JbTV0UOm6F7uOCKOmAZK~TiD2I-cjmme8CP9PT7D-ay1z31uCSvKdFC5ZSBu3rjGxufD8tNuBBxZsWgyHbX0rUGbDbCOdGuNnOl-fyMNGSkDnsj3l6ysA__"
    ),
    KB(
        "국민카드",
        KBColor,
        "https://s3-alpha-sig.figma.com/img/39d4/38c2/256522d08864af7038cea1f040325750?Expires=1725840000&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=VAnCfmqr-yPyQn-aZx9kCFQH29oHd2u~eS1MOboWIoSOzKMgGyF49KnVTYwrdisa1Ti0ZzCMO~QUrH7qhRPVa90WDSyOoZendkw2epQc8ZK3UPSUMZoSwzapRycoq91lrUXiv652dYlRuTDLrH8JfkjwuMzEZXSqjECxtnVklQLhXEgNSeXnXzlEYxtb8s2s62iAN5ZTBCMt-7cV8WEaNAd8mFl1~e7NfGziA4KxiFBlwFgpgi5D5g78SQHpSCGjKRQreWtNkx7H9yEF9J1INta6FCf82FJfBPCJrBN~eWzGhD62rSo39GQ5~PoeqR9ne3eLWR9Om4iH2-MwtUcZfQ__"
    ),
}
