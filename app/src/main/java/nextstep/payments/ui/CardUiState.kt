package nextstep.payments.ui

import nextstep.payments.data.model.Card

sealed interface CardUiState<out T> {
    object Empty : CardUiState<Nothing>
    data class One(val data: Card) : CardUiState<List<Card>>
    data class Many(val data: List<Card>) : CardUiState<List<Card>>
}