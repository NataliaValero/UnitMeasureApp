package com.example.temperatureapp.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

class DataStoreFactory {

    companion object{
        private const val PREFERENCES_MEASURES = "measures_preferences"
        private val Context.dataStore  by preferencesDataStore(name = PREFERENCES_MEASURES)

        fun getStore(context: Context) = context.dataStore
    }
}