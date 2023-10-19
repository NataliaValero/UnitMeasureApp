package com.example.temperatureapp.model.MeasureConverters

import com.example.temperatureapp.model.MeasureUnits

class WeightConverter: Converter {

    override fun convertValues(value: Float, fromUnit: MeasureUnits): List<Float> {

        val weightsValues = mutableListOf<Float>()

        MeasureUnits.getWeights().forEach{ conversion ->
            val convertTo = conversion

            val result = when(fromUnit) {
                MeasureUnits.KILOGRAM -> convertFromKilograms(value, convertTo)
                MeasureUnits.GRAM -> convertFromGrams(value, convertTo)
                MeasureUnits.MILIGRAM -> convertFromMiligrams(value, convertTo)
                MeasureUnits.MICROGRAM -> convertFromMicrograms(value, convertTo)
                MeasureUnits.POUND -> convertFromPounds(value, convertTo)
                MeasureUnits.OUNCE ->convertFromOunces(value, convertTo)
                else -> value
            }

            weightsValues.add(result)
        }
        return weightsValues
    }


    private fun convertFromKilograms(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.GRAM -> value * 1000
            MeasureUnits.MILIGRAM -> value * 1000000
            MeasureUnits.MICROGRAM -> value * 1000000000
            MeasureUnits.POUND -> value / 0.45359237f
            MeasureUnits.OUNCE -> value * 35.27396f
            else -> value
        }
        return result
    }

    private fun convertFromGrams(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.KILOGRAM -> value / 1000
            MeasureUnits.MILIGRAM -> value * 1000
            MeasureUnits.MICROGRAM -> value * 1000000
            MeasureUnits.POUND -> value / 453.59237f
            MeasureUnits.OUNCE -> value / 28.34953f
            else -> value
        }
        return result
    }

    private fun convertFromMiligrams(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.KILOGRAM -> value / 1000000
            MeasureUnits.GRAM -> value / 1000
            MeasureUnits.MICROGRAM -> value * 1000
            MeasureUnits.POUND -> value / 453592.37f
            MeasureUnits.OUNCE -> value / 28349.52f
            else -> value
        }
        return result
    }



    private fun convertFromMicrograms(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.KILOGRAM -> value /  1000000000
            MeasureUnits.GRAM -> value / 1000000
            MeasureUnits.MILIGRAM -> value / 1000
            MeasureUnits.POUND -> value / 453592370f
            MeasureUnits.OUNCE -> value / 28349523.13f
            else -> value
        }
        return result
    }

    private fun convertFromPounds(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.KILOGRAM -> value * 0.45359236f
            MeasureUnits.GRAM -> value * 453.59238f
            MeasureUnits.MILIGRAM -> value * 453592.38f
            MeasureUnits.MICROGRAM -> value * 453592370
            MeasureUnits.OUNCE -> value * 16f
            else -> value
        }
        return result
    }


    private fun convertFromOunces(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.KILOGRAM -> value / 35.27396f
            MeasureUnits.GRAM -> value * 28.34952f
            MeasureUnits.MILIGRAM -> value * 28349.52f
            MeasureUnits.MICROGRAM -> value * 28349523.13f
            MeasureUnits.POUND -> value * 16f
            else -> value
        }
        return result
    }

}