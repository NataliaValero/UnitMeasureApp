package com.example.temperatureapp.repository


import com.example.temperatureapp.data.MeasuresDataSource
import com.example.temperatureapp.model.MeasureModel
import com.example.temperatureapp.model.MeasureType
import kotlinx.coroutines.flow.Flow


class MeasuresRepositoryImpl(private val dataSource: MeasuresDataSource) : MeasuresRepository {
    override suspend fun saveMeasureModel(measure: MeasureModel) {
        dataSource.saveMeasureModel(measure)
    }

    override suspend fun getMeasuresList(measureType: MeasureType): Flow<List<MeasureModel>> {
        return dataSource.getMeasuresList(measureType)
    }

    override suspend fun clearMeasuresList(measureType: MeasureType) {
        dataSource.clearMeasuresList(measureType)
    }

}

