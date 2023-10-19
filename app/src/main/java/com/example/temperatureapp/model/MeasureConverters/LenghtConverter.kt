package com.example.temperatureapp.model.MeasureConverters

import com.example.temperatureapp.model.MeasureUnits

class LenghtConverter: Converter {

    override fun convertValues(value: Float, fromUnit: MeasureUnits): List<Float> {
        val lenghtsValues = mutableListOf<Float>()

        MeasureUnits.getLenghts().forEach{conversion->
            val convertTo = conversion

            val result = when(fromUnit) {
                MeasureUnits.METER -> convertFromMeter(value, convertTo)
                MeasureUnits.KILOMETER -> convertFromKilometer(value, convertTo)
                MeasureUnits.CENTIMETER -> convertFromCentimeter(value, convertTo)
                MeasureUnits.MILIMETER -> convertFromMilimeter(value, convertTo)
                MeasureUnits.MILE -> convertFromMile(value, convertTo)
                MeasureUnits.YARD -> convertFromYard(value, convertTo)
                MeasureUnits.FOOT -> convertFromFoot(value, convertTo)
                MeasureUnits.INCH -> convertFromInch(value, convertTo)
                else -> value
            }

            lenghtsValues.add(result)

        }
        return lenghtsValues
    }


    private fun convertFromMeter(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.KILOMETER ->  value / 1000
            MeasureUnits.CENTIMETER -> value * 100
            MeasureUnits.MILIMETER -> value * 1000
            MeasureUnits.MILE -> value / 1609.34f
            MeasureUnits.YARD -> value * 1.09361f
            MeasureUnits.FOOT -> value * 3.28084f
            MeasureUnits.INCH -> value * 39.37008f
            else -> value
        }
        return result
    }

    private fun convertFromKilometer(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.METER ->  value * 1000
            MeasureUnits.CENTIMETER -> value * 100000
            MeasureUnits.MILIMETER -> value * 1000000
            MeasureUnits.MILE -> value / 1.60934f
            MeasureUnits.YARD -> value * 1093.61f
            MeasureUnits.FOOT -> value * 3280.84f
            MeasureUnits.INCH -> value * 39370.08f
            else -> value
        }
        return result
    }


    private fun convertFromCentimeter(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.METER ->  value / 100
            MeasureUnits.KILOMETER -> value / 100000
            MeasureUnits.MILIMETER -> value * 10
            MeasureUnits.MILE -> value / 160934.4f
            MeasureUnits.YARD -> value / 91.44f
            MeasureUnits.FOOT -> value / 30.48f
            MeasureUnits.INCH -> value / 2.54f
            else -> value
        }
        return result
    }

    private fun convertFromMilimeter(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.METER ->  value / 1000
            MeasureUnits.CENTIMETER -> value / 10
            MeasureUnits.KILOMETER -> value / 1000000
            MeasureUnits.MILE -> value / 1609344
            MeasureUnits.YARD -> value / 914.4f
            MeasureUnits.FOOT -> value / 304.8f
            MeasureUnits.INCH -> value / 25.4f
            else -> value
        }
        return result
    }

    private fun convertFromMile(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.KILOMETER ->  value * 1.60934f
            MeasureUnits.METER -> value * 1609.34f
            MeasureUnits.CENTIMETER-> value * 160934.4f
            MeasureUnits.MILIMETER -> value * 1609344
            MeasureUnits.YARD -> value * 1760
            MeasureUnits.FOOT -> value * 5280
            MeasureUnits.INCH -> value * 63360
            else -> value
        }
        return result
    }

    private fun convertFromYard(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.KILOMETER ->  value / 1094f
            MeasureUnits.METER -> value * 0.9144f
            MeasureUnits.CENTIMETER-> value * 91.44f
            MeasureUnits.MILIMETER-> value * 914.4f
            MeasureUnits.MILE -> value / 1760
            MeasureUnits.FOOT -> value * 3
            MeasureUnits.INCH -> value * 36
            else -> value
        }
        return result
    }


    private fun convertFromFoot(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.KILOMETER ->  value / 3280.84f
            MeasureUnits.METER -> value * 0.3048f
            MeasureUnits.CENTIMETER-> value * 30.48f
            MeasureUnits.MILIMETER-> value * 304.8f
            MeasureUnits.MILE -> value / 5280f
            MeasureUnits.YARD -> value / 3f
            MeasureUnits.INCH -> value * 12
            else -> value
        }
        return result
    }

    private fun convertFromInch(value: Float, convertTo: MeasureUnits) : Float{

        val result = when(convertTo) {
            MeasureUnits.KILOMETER ->  value / 39370.08f
            MeasureUnits.METER -> value * 0.0254f
            MeasureUnits.CENTIMETER-> value * 2.54f
            MeasureUnits.MILIMETER-> value * 25.4f
            MeasureUnits.MILE -> value / 63360f
            MeasureUnits.YARD -> value / 36
            MeasureUnits.FOOT -> value / 12
            else -> value
        }
        return result
    }
}