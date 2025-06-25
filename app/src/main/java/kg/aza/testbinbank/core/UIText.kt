package kg.aza.testbinbank.core

import android.content.Context
import androidx.annotation.StringRes

class UIText(
    private val context: Context
) {
    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

    fun getString(@StringRes stringResId: Int, vararg formatArgs: Any): String {
        return context.getString(stringResId, *formatArgs)
    }
}