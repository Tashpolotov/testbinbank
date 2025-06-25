package kg.balance.reforged.foundation.logic

import kotlin.enums.enumEntries

inline fun <reified T : Enum<T>> findEnumByName(value: String?, default: T): T {
    return enumEntries<T>().find { it.name.replace("_", "").equals(value, ignoreCase = true) }
        ?: default
}
