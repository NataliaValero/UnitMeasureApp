package com.example.temperatureapp.model

import kotlinx.serialization.Serializable

@Serializable
data class MeasureModel(val measure: Float, val convertFrom: String, val measureType: MeasureType)

