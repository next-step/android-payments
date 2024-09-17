package nextstep.payments.model

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import nextstep.payments.ui.PaymentCard

sealed interface CardCompanyType {
    val name: String
    val color: Long

    @Composable
    fun Render()

    data object None : CardCompanyType {
        override val name = ""
        override val color = 0xFF333333

        @Composable
        override fun Render() {
            PaymentCard(
                modifier = Modifier.background(
                    color = Color(color),
                    shape = RoundedCornerShape(5.dp),
                ),
            )
        }
    }

    data class Bc(override val name: String) : CardCompanyType {
        override val color = 0xFFF04651

        @Composable
        override fun Render() {
            PaymentCard(
                modifier = Modifier
                    .background(
                        color = Color(color),
                        shape = RoundedCornerShape(5.dp),
                    ),
                cardCompany = name
            )
        }
    }

    data class Shinhan(override val name: String) : CardCompanyType {
        override val color = 0xFF0E19ED

        @Composable
        override fun Render() {
            PaymentCard(
                modifier = Modifier
                    .background(
                        color = Color(color),
                        shape = RoundedCornerShape(5.dp),
                    ),
                cardCompany = name
            )
        }
    }

    data class Kakaobank(override val name: String) : CardCompanyType {
        override val color = 0xFFF0DE1F

        @Composable
        override fun Render() {
            PaymentCard(
                modifier = Modifier
                    .background(
                        color = Color(color),
                        shape = RoundedCornerShape(5.dp),
                    ),
                cardCompany = name
            )
        }
    }

    data class Hyundai(override val name: String) : CardCompanyType {
        override val color = 0xFF030201

        @Composable
        override fun Render() {
            PaymentCard(
                modifier = Modifier
                    .background(
                        color = Color(color),
                        shape = RoundedCornerShape(5.dp),
                    ),
                cardCompany = name
            )
        }
    }

    data class Woori(override val name: String) : CardCompanyType {
        override val color = 0xFF416CE0

        @Composable
        override fun Render() {
            PaymentCard(
                modifier = Modifier
                    .background(
                        color = Color(color),
                        shape = RoundedCornerShape(5.dp),
                    ),
                cardCompany = name
            )
        }
    }

    data class Lotte(override val name: String) : CardCompanyType {
        override val color = 0xFFED2405

        @Composable
        override fun Render() {
            PaymentCard(
                modifier = Modifier
                    .background(
                        color = Color(color),
                        shape = RoundedCornerShape(5.dp),
                    ),
                cardCompany = name
            )
        }
    }

    data class Hana(override val name: String) : CardCompanyType {
        override val color = 0xFF0CAB73

        @Composable
        override fun Render() {
            PaymentCard(
                modifier = Modifier
                    .background(
                        color = Color(color),
                        shape = RoundedCornerShape(5.dp),
                    ),
                cardCompany = name
            )
        }
    }

    data class Kb(override val name: String) : CardCompanyType {
        override val color = 0xFF695F54

        @Composable
        override fun Render() {
            PaymentCard(
                modifier = Modifier
                    .background(
                        color = Color(color),
                        shape = RoundedCornerShape(5.dp),
                    ),
                cardCompany = name
            )
        }
    }
}
