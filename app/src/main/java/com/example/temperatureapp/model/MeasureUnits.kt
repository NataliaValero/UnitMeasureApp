package com.example.temperatureapp.model

import com.example.temperatureapp.model.MeasureCategory.*

enum class MeasureUnits (var measureCategory: MeasureCategory, var measureName: String, var measureSymbol: String){

    // Temperature units
    CELSIUS(TEMPERATURE, "Celsius", "°C"),
    KELVIN(TEMPERATURE, "Kelvin", "K"),
    FAHRENHEIT(TEMPERATURE, "Fahrenheit", "°F"),

    // Weight units
    KILOGRAM(WEIGHT, "Kilogram", "Kg"),
    GRAM(WEIGHT, "Gram", "g"),
    MILIGRAM(WEIGHT, "Miligram", "mg"),
    MICROGRAM(WEIGHT, "Microgram", "µg"),
    POUND(WEIGHT, "Pound", "lb"),
    OUNCE(WEIGHT, "Ounce", "oz"),

    //Lenght units

    METER(LENGTH, "Meter", "m"),
    KILOMETER(LENGTH, "Kilometer", "km"),
    CENTIMETER(LENGTH, "Centimeter", "cm"),
    MILIMETER(LENGTH, "Milimeter", "mm"),
    MILE(LENGTH, "Mile", "mi"),
    YARD(LENGTH, "Yard", "yd"),
    FOOT(LENGTH, "Foot", "ft"),
    INCH(LENGTH, "Inch", "in"),

    // Area units
    SQUAREMETER(AREA, "Square Meter", "m^2"),
    SQUAREKILOMETER(AREA, "Squater Kilometer", "km^2"),
    SQUARECENTIMETER(AREA, "Squater Centimeter", "cm^2"),
    SQUAREMILIMETER(AREA, "Squater Milimeter", "mm^2"),
    HECTARE(AREA, "Hectare", "ha"),
    SQUAREMILE(AREA, "Squater Mile", "mi^2"),
    SQUAREYARD(AREA, "Squater Yard", "yd^2"),
    SQUAREFOOT(AREA, "Squater Foot", "ft^2"),
    SQUAREINCH(AREA, "Squater Inch", "in^2"),

    // Volume units
    CUBICMETER(VOLUME, "Cubic Meter", "m^3"),
    CUBICKILOMETER(VOLUME, "Cubic Kilometer", "km^3"),
    CUBICCENTIMETER(VOLUME, "Cubic Centimeter", "cm^3"),
    CUBICMILIMETER(VOLUME, "Cubic Milimeter", "mm^3"),
    LITER(VOLUME, "Liter", "L"),
    MILILITER(VOLUME, "Milimeter", "mL"),
    GALON(VOLUME, "US Galon", "gal"),
    QUART(VOLUME, "US Quart", "qt"),
    PINT(VOLUME, "US Pint", "pt"),
    CUP(VOLUME, "US Quart", "c"),
    FLUIDOUNCE(VOLUME, "Fluid Ounce", "fl oz"),
    TABLESPOON(VOLUME, "Table Spoon", "Tbsp"),
    TEASPOON(VOLUME, "Tea Spoon", "tsp"),

    // time units
    SECOND(TIME, "Second", "s"),
    MINUTE(TIME, "Minute", "min"),
    HOUR(TIME, "Hour", "hr"),
    DAY(TIME, "Day", "d"),
    WEEK(TIME, "Week", "wk"),
    MONTH(TIME, "Month", "mo"),
    YEAR(TIME, "Year", "yr");



    companion object {

        fun getTemperatures()= values().filter { it.measureCategory == TEMPERATURE }
        fun getWeights() = values().filter { it.measureCategory == WEIGHT }
        fun getLenghts() = values().filter { it.measureCategory == LENGTH }
        fun getAreas() = values().filter { it.measureCategory == AREA }
        fun getVolumes() = values().filter { it.measureCategory == VOLUME }
        fun getTimes() = values().filter { it.measureCategory == TIME }

        fun getUnits(measureCategory: MeasureCategory): List<MeasureUnits> {
            return when(measureCategory) {
                TEMPERATURE -> getTemperatures()
                WEIGHT -> getWeights()
                LENGTH -> getLenghts()
                AREA -> getAreas()
                VOLUME -> getVolumes()
                TIME -> getTimes()
            }
        }

    }

}