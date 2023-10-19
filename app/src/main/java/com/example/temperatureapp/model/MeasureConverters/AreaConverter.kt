package com.example.temperatureapp.model.MeasureConverters

import com.example.temperatureapp.model.MeasureUnits

class AreaConverter : Converter{
    override fun convertValues(value: Float, fromUnit: MeasureUnits): List<Float> {
        val areasValues = mutableListOf<Float>()

        MeasureUnits.getAreas().forEach{ conversion->

            val convertTo = conversion

            val result = when(fromUnit) {
                MeasureUnits.SQUAREMETER-> convertFromSquareMeter(value, convertTo)
                MeasureUnits.SQUAREKILOMETER -> convertFromSquareKilometer(value, convertTo)
                MeasureUnits.SQUARECENTIMETER -> convertFromSquareCentimeter(value, convertTo)
                MeasureUnits.SQUAREMILIMETER -> convertFromSquareMilimeter(value, convertTo)
            }
        }


        return areasValues
    }

    private fun convertFromSquareMeter(value: Float, convertTo: MeasureUnits) : Float {
        val result = when(convertTo) {
            MeasureUnits.SQUAREKILOMETER -> value / 1000000
            MeasureUnits.SQUARECENTIMETER -> value * 10000
            MeasureUnits.SQUAREMILIMETER -> value * 1000000
            MeasureUnits.HECTARE -> value / 10000
            MeasureUnits.SQUAREMILE -> value / 2589988.11f
            MeasureUnits.SQUAREYARD -> value * 1.19599f
            MeasureUnits.SQUAREFOOT -> value * 10.78391f
            MeasureUnits.SQUAREINCH -> value * 1550.0031f
            else -> value
        }
        return result
    }


    private fun convertFromSquareKilometer(value: Float, convertTo: MeasureUnits) : Float {
        val result = when(convertTo) {
            MeasureUnits.SQUAREMETER -> value * 1000000
            MeasureUnits.SQUARECENTIMETER -> value * 10000000
            MeasureUnits.SQUAREMILIMETER -> value * 10000000000
            MeasureUnits.HECTARE -> value * 100
            MeasureUnits.SQUAREMILE -> value / 2.58998811f
            MeasureUnits.SQUAREYARD -> value * 1195990.05f
            MeasureUnits.SQUAREFOOT -> value * 10763910.42f
            MeasureUnits.SQUAREINCH -> value * 1550003100.01f
            else -> value
        }
        return result
    }

    private fun convertFromSquareCentimeter(value: Float, convertTo: MeasureUnits) : Float {
        val result = when(convertTo) {
            MeasureUnits.SQUAREMETER -> value / 10000
            MeasureUnits.KILOMETER -> value / 10000000
            MeasureUnits.SQUAREMILIMETER -> value * 100
            MeasureUnits.HECTARE -> value / 10000
            MeasureUnits.SQUAREMILE -> value / 2589988110f
            MeasureUnits.SQUAREYARD -> value / 8361.27f
            MeasureUnits.SQUAREFOOT -> value / 929.0304f
            MeasureUnits.SQUAREINCH -> value / 6.4516f
            else -> value
        }
        return result
    }

    private fun convertFromSquareMilimeter(value: Float, convertTo: MeasureUnits) : Float {
        val result = when(convertTo) {
            MeasureUnits.SQUARECENTIMETER -> value /100
            MeasureUnits.SQUAREMETER -> value / 1000000
            MeasureUnits.KILOMETER -> value / 1000000000
            MeasureUnits.HECTARE -> value / 10000000
            MeasureUnits.SQUAREMILE -> value / 2589988110336
            MeasureUnits.SQUAREYARD -> value / 836127.36f
            MeasureUnits.SQUAREFOOT -> value / 9290.304f
            MeasureUnits.SQUAREINCH -> value / 645.16f
            else -> value
        }
        return result
    }



}