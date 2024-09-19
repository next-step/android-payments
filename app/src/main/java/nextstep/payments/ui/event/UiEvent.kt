package nextstep.payments.ui.event

internal interface UiEvent<T> {
    val data: T
    fun onProcessed()
}

internal fun <T> UiEvent(data: T, onProcessed: () -> Unit): UiEvent<T> {
    return object : UiEvent<T> {
        override val data: T = data

        override fun onProcessed() = onProcessed()
    }
}