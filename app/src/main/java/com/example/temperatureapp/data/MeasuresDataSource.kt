package com.example.temperatureapp.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.temperatureapp.model.MeasureModel
import com.example.temperatureapp.model.MeasureType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class MeasuresDataSource(private val dataStore: DataStore<Preferences>) {

    // Definición de claves de preferencias
    private object PreferencesKey {
        val temperatureListKey = stringPreferencesKey("my_temperatures_list")
        val weightListKey = stringPreferencesKey("my_weight_list")
    }

    // Guardar una medida en la lista correspondiente usando JSON
    suspend fun saveMeasureModel(newMeasure: MeasureModel) {
        val listFlow = getMeasuresList(newMeasure.measureType)

        listFlow.first().let { currentList ->
            val newList = currentList + newMeasure
            val key = getPreferencesKey(newMeasure.measureType)

            dataStore.edit { preferences ->
                preferences[key] = Json.encodeToString(newList)
            }
        }
    }

    // Obtener una lista de medidas según el tipo de medida
    fun getMeasuresList(measureType: MeasureType): Flow<List<MeasureModel>> {
        val measureList = when (measureType) {
            MeasureType.TEMPERATURE -> temperaturesListPreferencesFlow
            MeasureType.WEIGHT -> weightListPreferencesFlow
        }

        return measureList
    }

    // Obtener la clave de preferencia correspondiente al tipo de medida
    private fun getPreferencesKey(measureType: MeasureType): Preferences.Key<String> {
        return when (measureType) {
            MeasureType.TEMPERATURE -> PreferencesKey.temperatureListKey
            MeasureType.WEIGHT -> PreferencesKey.weightListKey
        }
    }

    private val json = Json { ignoreUnknownKeys = true }

    // Listas de medidas con JSON
    val temperaturesListPreferencesFlow: Flow<List<MeasureModel>> =
        createMeasureListFlow(PreferencesKey.temperatureListKey)

    val weightListPreferencesFlow: Flow<List<MeasureModel>> =
        createMeasureListFlow(PreferencesKey.weightListKey)

    // Crear un flujo de lista de medidas
    private fun createMeasureListFlow(key: Preferences.Key<String>): Flow<List<MeasureModel>> {
        return dataStore.data
            .map { preferences ->
                val jsonString = preferences[key] ?: "[]"
                val lista = json.decodeFromString<List<MeasureModel>>(jsonString)
                lista
            }
    }

    // Borrar una lista de medidas según el tipo de medida
    suspend fun clearMeasuresList(measureType: MeasureType) {
        val preferencesKey = getPreferencesKey(measureType)

        dataStore.edit { preferences ->
            preferences.remove(preferencesKey)
        }

//      dataStore.edit {
//            it.clear()
//        }

    }


}
