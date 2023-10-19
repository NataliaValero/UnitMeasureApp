package com.example.temperatureapp.model

import kotlinx.serialization.Serializable


@Serializable
enum class MeasureType {
    TEMPERATURE,
    WEIGHT
}