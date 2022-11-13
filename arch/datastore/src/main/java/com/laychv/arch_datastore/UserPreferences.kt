package com.laychv.arch_datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(
    context: Context
) {
    private val applicationContext = context.applicationContext
    private val dataStore: DataStore<Preferences> = applicationContext.createDataStore(
        name = "app_preferences"
    )

    val bookmark: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_BOOKMARK]
        }

    suspend fun saveBookmark(bookmark: String) {
        dataStore.edit { preferences ->
            preferences[KEY_BOOKMARK] = bookmark
        }
    }

    suspend fun clearBookmark() {
        dataStore.edit { it[KEY_BOOKMARK] }
    }

    companion object {
        val KEY_BOOKMARK = preferencesKey<String>("key_bookmark")
    }
}