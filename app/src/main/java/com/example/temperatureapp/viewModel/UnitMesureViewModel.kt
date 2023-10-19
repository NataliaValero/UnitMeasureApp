package com.example.temperatureapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.liveData
import com.example.temperatureapp.model.MeasureConverters.Converter
import com.example.temperatureapp.model.MeasureCategory
import com.example.temperatureapp.model.MeasureModel
import com.example.temperatureapp.model.MeasureType
import com.example.temperatureapp.model.MeasureUnits
import com.example.temperatureapp.repository.MeasuresRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UnitMesureViewModel(private val measuresRepository: MeasuresRepository) : ViewModel() {
    var unitList = emptyList<MeasureUnits>()

    // Initialize variables
    private lateinit var convertFrom: MeasureUnits

    // Default category
    private var selectedCategory: MeasureCategory = MeasureCategory.TEMPERATURE


    fun setMeasureCategory(category: MeasureCategory) {


        this.selectedCategory = category

        // Cuando elige una categoria carga el unitlist
        // Set default convertFrom
        unitList = MeasureUnits.getUnits(category)
        refreshCalculation(value = 0f)

    }

    fun getMeasureCategory() : MeasureCategory {
        return selectedCategory
    }

    // LiveData to hold calculation results
    val calculationResult: MutableLiveData<List<Float>> by lazy {
        MutableLiveData<List<Float>>()
    }

    companion object {
        const val TAG = "UnitMesureViewModel"
    }

    // Function to save a measure asynchronously
    fun saveMeasure(measure: MeasureModel) {
        viewModelScope.launch {
            measuresRepository.saveMeasureModel(measure)
        }
    }


    // Function to get measures LiveData for a specific type
    // Returns LiveData containing a lisf of measure for the specified type
    fun getMeasuresLiveData(measureType: MeasureType) = liveData(Dispatchers.IO) {
        emit(measuresRepository.getMeasuresList(measureType).first())
    }

    // Function to clear measures LiveData for a specific type
    fun clearMeasuresLiveData(measureType: MeasureType) {
        viewModelScope.launch {
            measuresRepository.clearMeasuresList(measureType)
        }
    }


    // Function to convert a value to multiple units of measurement
    // Returns a list of converted values
    private fun returnAllConvertion(value: Float, convertFrom: MeasureUnits) : List<Float> {

        val converter = Converter.getConverter(convertFrom.measureCategory)

        converter?:return unitList.map{0f}

        return converter.convertValues(value, convertFrom)

    }

    // Function to refresh temperature conversion results
    // Returns a list of updated temperature conversion results
    // Unitlist.firstornull toma el primero de la lista de units
    fun refreshCalculation(convertFrom: MeasureUnits? = unitList.firstOrNull(), value: Float = 0.0f) {
        convertFrom?:return

        this.convertFrom = convertFrom

        calculationResult.value = returnAllConvertion(value, convertFrom )
    }


}

// Factory class to create UnitMeasureViewModel instances
class UnitMesureVMFactory(private val measuresRepository: MeasuresRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MeasuresRepository::class.java).newInstance(measuresRepository)
    }
}

