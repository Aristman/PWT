@file:Suppress("EqualsOrHashCode") // ktlint-disable filename

package ru.marslab.pocketwordtranslator.presentation.common.model // ktlint-disable filename

import androidx.annotation.StringRes
import ru.marslab.pocketwordtranslator.core.Event

sealed class BaseAppEvent : Event {
    data class SnackBarEvent(
        val message: String? = null,
        @StringRes val resId: Int? = null,
        val delay: Long = 2000L
    ) : BaseAppEvent() {
        override fun equals(other: Any?): Boolean = false
    }

    data class ToastEvent(
        val message: String? = null,
        @StringRes val resId: Int? = null
    ) : BaseAppEvent() {
        override fun equals(other: Any?): Boolean = false
    }
}
