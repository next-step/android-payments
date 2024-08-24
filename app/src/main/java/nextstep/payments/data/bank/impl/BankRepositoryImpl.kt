package nextstep.payments.data.bank.impl

import androidx.compose.ui.graphics.Color
import nextstep.payments.data.Bank
import nextstep.payments.data.BankType
import nextstep.payments.data.bank.BankRepository

class BankRepositoryImpl : BankRepository {
    override fun getBanks(): List<Bank> {
        return listOf(
            Bank(
                BankType.BC,
                "BC카드",
                "https://s3-alpha-sig.figma.com/img/ec2e/412b/5dbba0f6ed87192ed46b92e2e3aec83e?Expires=1725235200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=S0NCqFkEW47pwO~88wUPKsA1X3DO7L8m1VpxCP6yCNu0xfHiqSv-QmK72ohT7wz7Ugzeh19rzHvfNNd8lw8E9Kgdnks~lnumits0-hAqoiXRaZ0lD~YU0VghOm3wfJRtUQ4I8rKqA~U-anEgO3Q1LUv3p2PfD5hUWPmukei-g6DdpZGbsP4FPBESbR-ykFzwcwQZaSAQ~1e34DSdopnVu~1WWiz~UDiUFEPRXZUpnn9VbHCtvXGFsVJQLDybeN9EayL9qdWfY77yqPc6U3zwkebbGIk7-p03o-Y53~10u0FOQGf06iRQ4CTmFItgmx8KDZYU4NnX-ZLkXd4fXDFs7Q__",
                Color.Red
            ),
            Bank(
                BankType.SHINHAN,
                "신한카드",
                "https://s3-alpha-sig.figma.com/img/8cb5/ff9c/17af119128d7b34b76c54c1828dfcf98?Expires=1725235200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=Afn0yTYSYiRuuyTB9kdGouVPemBDWoBoeSyiYSPcM6sbhPNBzoYsQ-ECHYNhwU3JIzkZ-QuDVY-ocT~CONToK4qnQQIVg42-CY7K7dB8ghEb0wT6Zqkg8xeXeVuZs7c6QBd~Sm0FRaE~1ICuheEhnBBCuzo1V4sgzkskhcMYfmQf7zi5ln5apO92LmpN1~idGbrhX6gqG~2Zg42E3Hzz-o8f-emGM81C0NuDCaybcdfWts5kVT3llSHCWoBNeYqvATenoacbw7boxetWdh2aA1ma3hhm1swsUETNQPbChqkmgVY~~McJopyCZ517Yh6fEzdiuUMFeXG~aNmOTAU4Fw__",
                Color.Blue
            ),
            Bank(
                BankType.KAKAO,
                "카카오뱅크",
                "https://s3-alpha-sig.figma.com/img/62b3/d8f9/798f98848393b3bd58d726356ecfe1d4?Expires=1725235200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=AdbgYzdt78qcuHDcfoLB1ocGbfmeWMXVq90tQTTxF8gnC6aY-gyUAqlsOoU2PEaeS1skchdhfbf4swAZnta4FXdpEeS0BRa9XUbaHwAbmZCDXB-Vcld8qg9tFoV~ENYDrceqgNrsHH-JU9IEYKSCyiIkioGSeu5RSORn0-q6H24ENZ4UqdV1Ws42F-hEv2BOE3y4V9jbpodfpu-5u9rY4dN97qIwFbWNqCXbwAYOkRdj86001DhR1BAdsl2814UWJwjLAR7Nzjgy-SlT7JyAiDH4xW87Z08tR2P7NjIVHMDv-zLBxMXF-0HQeodDnJqhlwoq1TQydQXhOCbyfspvOQ__",
                Color.Yellow
            ),
            Bank(
                BankType.HYUNDAI,
                "현대카드",
                "https://s3-alpha-sig.figma.com/img/97bf/c00e/ce47fbe052835f468965626c191e75de?Expires=1725235200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=BiqP6fcA~gz24UR8~Qng01h8S6VXrg6EjQw6dPTQP0~Q9n3h~H3fJjZywBajctz33FeoF4yywGp9FbEYoHPhOen79HtnQHtpEoN~AsnmT-TdnkRF3nKb-0JEpQJbORY2yWapv3ZSAq~zTw9oSuf14MP-Kg~McqUQjFtAVECZCZE5eGOkdxldVf-smTnSsiZtk7di2ONdGLWdxKduTS081N8kYqSRVTgPnqi-k2hWk4y8yxBbwFhwJFWqCze-xVa1I9AyhqkxnOqlftIJWfuzJq2W9-a4UBqw49~4CbFsLLnHWhWpsCknn4sAIpDECYB1DLYxWZNcyo~3OuP0PYhAxw__",
                Color.Black
            ),
            Bank(
                BankType.WOORI,
                "우리카드",
                "https://s3-alpha-sig.figma.com/img/14be/d1aa/29bb17713330074966c9944e0ee37e96?Expires=1725235200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=nmEZo~fpUaqNilctdjKmtNtPne~JXPPI6oor56-d5UyHOE6WL1tGeV3hMnPUQEcycfgfRkCNkEwGgDKSBj2m9gLrkOWEnlmMP7eoySNQ45Rxel6WqzfvQu-2RVRW9HXVD7nuIJQ96a9jUEBrmRLTyoP~sM1E1ygsoNoBAJah7Fvv1v3tCqDw61ooZY5bZj3w9l1tFcWkh62j~oiXnUBk6qaPgcazdS3j~6vi06ScXcqVovSnn2CREIzw23uVeQAraikI87fMauhKmfGlutZWFERvkXfXVf92H3d-kB18PLHGbOtrR66qwnl5LGbnUt5xnDaQYKBad9UnZcE--jSv1A__",
                Color.Cyan
            ),
            Bank(
                BankType.LOTTE,
                "롯데카드",
                "https://s3-alpha-sig.figma.com/img/c70a/5f12/c602b6ca83ab8e98b3f68ac17c4373e0?Expires=1725235200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=WonB4GGKdErXxVbIiejsX5MrJTtIjIGR4ENWXDfISw~2vPqADUFsfcYhEScsSKaOZyXoVWN8wdZCPDkfkiAGJffLX7~9f6JvQmE2EC~oFHPe2Z-uIlEpPj5tBD0zqZ9BK5Fk3ic4XrSTdD5zrkVNAHgzMMIIGaGW-DnaYmNmTVouoigc0mFaz~jZRLtIU25UywcvCcDzkzff95jWQgi5SFyf2vfPwLaKPOnV1mPr8i7v5YwpFBEPx4jUz1ED2ZBYLJPCDHmw8Ye2mY4V4tJKfZItm7HXRr204ahkj4nGKxaAkxInTh9XjM~EnuVJyn7PuPR24m9--vwRrLX2tZzu1Q__",
                Color.Red
            ),
            Bank(
                BankType.HANA,
                "하나카드",
                "https://s3-alpha-sig.figma.com/img/001f/6745/31818ebbc2f3194a256a192eb0186f9e?Expires=1725235200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=hd1BPT~GhnOwBaVtk~ZrO5YcI9QRY5ZbfQTgmO9aE~zDmcoderGNNQ7LysAgiwJ9fdLwRPOOW6gAJlDlE1x6YeHxGdLRJk18wYbu41Vnh09-7avoc08f15A~hDs51sLPAWchEFzu0peBrWAi5~6YLxsUawF3hHBtCfmeBS5XO3tHAnEfZ84pFnQodErIQxTp7yK1KA0-RQ~-30D2-LEyttLb-NmVWw9JwKFX4s8Y~BZrlAx8UPs2eeLkVgs2~v3OQqhKu8MdPnkOkpgvNhPeKXBH3XJSHVBIUUrAsrblawzuCMLZ8T4vDPBWWDRIetL18P-JHu-aq-7dUKUok5R5zA__",
                Color.Green
            ),
            Bank(
                BankType.KB,
                "국민카드",
                "https://s3-alpha-sig.figma.com/img/39d4/38c2/256522d08864af7038cea1f040325750?Expires=1725235200&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=S2-50CgHelUqTJLV2byb4Od2o8Z1gmNKSxF7qbcd0ZJkRKtauJ217qxk27zzJy-Uvm64m-3c7G-OgUCy9Fld7qFYJ2Cxdobsj8igyOe~mDZTwSyjh2tElQ8WErrfzNEd6TO5MLOuLdQs6t3HoTDHmcB6mYjCHmEIovs6tlAcbFWnQlbKo~yeJIq8Uxk7-yso6AHHS1alqFf49e5HOVhMEKqmG4m4b4o8-W4hdb-Qvx0-ibLDNrQG1c0zTdgW~9mQokeiuFwKz~ipvP1Y5svFjqP8xQoX6~xyZr~tZvEIsPkb9iVRQumCUZfaCrWc9ToRCTTLZQZMcw-79ieibm8JgA__",
                Color.DarkGray
            )
        )
    }
}