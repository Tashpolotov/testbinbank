package kg.balance.reforged.foundation.infrastructure.local.preferences

import android.content.SharedPreferences
import android.util.Log
import kg.aza.testbinbank.presentation.view.models.HistoryEntryUI
import kg.aza.testbinbank.presentation.view.models.InfoUIModel
import kg.aza.testbinbank.presentation.view.models.toHistoryEntry
import kg.balance.reforged.foundation.infrastructure.local.Preferences
import kotlinx.serialization.json.Json

class UserPreferences(preferences: SharedPreferences, json: Json) : Preferences(preferences, json) {

    var historyList by nonPrimitive(default = emptyList<HistoryEntryUI>())

    fun addToHistory(bin: String, info: InfoUIModel) {
        if (bin.length != 6) return
        if (info.scheme.isNullOrBlank() && info.bank?.name.isNullOrBlank()) return

        val updatedList = historyList.toMutableList()
        updatedList.removeAll { it.bin == bin }
        updatedList.add(0, info.toHistoryEntry(bin))
        historyList = updatedList
        Log.d("Aza1", "historyList updated: $historyList")
    }
}