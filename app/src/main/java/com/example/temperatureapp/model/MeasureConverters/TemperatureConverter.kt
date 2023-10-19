package com.example.temperatureapp.model.MeasureConverters

import com.example.temperatureapp.model.MeasureUnits

class TemperatureConverter : Converter {


    override fun convertValues(value: Float, fromUnit: MeasureUnits): List<Float> {
        val temperaturesValues = mutableListOf<Float>()

        MeasureUnits.getTemperatures().forEach { conversion ->
            val convertTo = conversion


            // Perform unit conversion based on the 'convertFrom' unit
            val result = when(fromUnit) {
                MeasureUnits.CELSIUS -> convertFromCelsius(value,convertTo)
                MeasureUnits.FAHRENHEIT -> convertFromFahrenheit(value, convertTo)
                MeasureUnits.KELVIN -> convertFromKelvin(value, convertTo)
                else -> value
            }

            temperaturesValues.add(result)
        }

        return temperaturesValues
    }



    private fun convertFromCelsius(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.FAHRENHEIT -> (value * 9/5) + 32
            MeasureUnits.KELVIN -> value + 273.15f
            else -> value
        }
        return result
    }

    private fun convertFromFahrenheit(value: Float, convertTo: MeasureUnits): Float {

        val result = when(convertTo) {
            MeasureUnits.CELSIUS -> 5.0f / 9 * (value - 32)
            MeasureUnits.KELVIN -> 5.0f / 9 * (value + 459.67f)
            else -> value
        }
        return result
    }

    private fun convertFromKelvin(value: Float, convertTo: MeasureUnits): Float {

        val result = when(convertTo) {
            MeasureUnits.CELSIUS -> value - 273.15f
            MeasureUnits.FAHRENHEIT -> value * (9/5 - 459.67f)
            else -> value
        }
        return result
    }




}