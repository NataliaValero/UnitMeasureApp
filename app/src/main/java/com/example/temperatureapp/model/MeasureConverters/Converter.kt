package com.example.temperatureapp.model.MeasureConverters

import com.example.temperatureapp.model.MeasureCategory
import com.example.temperatureapp.model.MeasureUnits

interface Converter {
    fun convertValues(value: Float, fromUnit: MeasureUnits) : List<Float>

    companion object{
        fun getConverter(measureCategory: MeasureCategory) : Converter? {
            return when(measureCategory){
                MeasureCategory.TEMPERATURE -> TemperatureConverter()
                MeasureCategory.WEIGHT -> WeightConverter()
                MeasureCategory.LENGTH -> LenghtConverter()
                else -> null
            }
        }
    }
}