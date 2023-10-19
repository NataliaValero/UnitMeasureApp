package com.example.temperatureapp.repository

import com.example.temperatureapp.model.MeasureModel
import com.example.temperatureapp.model.MeasureType
import kotlinx.coroutines.flow.Flow

interface MeasuresRepository {

    suspend fun saveMeasureModel(measure: MeasureModel)
    suspend fun getMeasuresList(measureType: MeasureType) : Flow<List<MeasureModel>>
    suspend fun clearMeasuresList(measureType: MeasureType)
}