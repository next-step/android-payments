package nextstep.payments.data.model

import androidx.compose.ui.graphics.Color

data class CardCompany(
    val code: Int,
    val name: String,
    val imageUrl: String,
    val color: Color = Color(0),
)

val cardCompanyList = listOf(
    CardCompany(
        code = 1,
        name = "BC카드",
        imageUrl = "https://github.com/user-attachments/assets/32ced813-3833-45b7-adaa-949be154f4f4",
        color = Color(0xFFE04D5B),
    ),
    CardCompany(
        code = 6,
        name = "신한카드",
        imageUrl = "https://github.com/user-attachments/assets/14df8df5-e3df-4600-927f-a9d565ceff25",
        color = Color(0xFF0044F4),
    ),
    CardCompany(
        code = 37,
        name = "카카오뱅크",
        imageUrl = "https://github.com/user-attachments/assets/29416871-f983-45fa-928e-ca97b9b98f33",
        color = Color(0xFFF2DB02),
    ),
    CardCompany(
        code = 7,
        name = "현대카드",
        imageUrl = "https://github.com/user-attachments/assets/3b3d27ee-9ae5-40e2-b207-d4762f72fa84",
        color = Color(0xFF000102),
    ),
    CardCompany(
        code = 15,
        name = "우리카드",
        imageUrl = "https://github.com/user-attachments/assets/e33afee4-4205-4851-a23a-4570acd1a1ed",
        color = Color(0xFF0076C0),
    ),
    CardCompany(
        code = 8,
        name = "롯데카드",
        imageUrl = "https://github.com/user-attachments/assets/ec709af9-c67e-4da3-93a3-4f69b5750b6d",
        color = Color(0xFFE11C24),
    ),
    CardCompany(
        code = 3,
        name = "하나카드",
        imageUrl = "https://github.com/user-attachments/assets/4eeea1f2-0417-4c32-bccf-1622b983ee42",
        color = Color(0xFF008E8B),
    ),
    CardCompany(
        code = 2,
        name = "국민카드",
        imageUrl = "https://github.com/user-attachments/assets/f92d101a-6876-475d-91d0-fdb7d456101d",
        color = Color(0xFF575B55),
    ),
)
