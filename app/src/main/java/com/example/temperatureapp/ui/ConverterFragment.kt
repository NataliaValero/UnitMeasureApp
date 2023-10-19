package com.example.temperatureapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentTemperatureBinding
import com.example.temperatureapp.data.DataStoreFactory
import com.example.temperatureapp.data.MeasuresDataSource
import com.example.temperatureapp.model.MeasureUnits
import com.example.temperatureapp.repository.MeasuresRepositoryImpl
import com.example.temperatureapp.ui.Adapter.ConvertFromAdapter
import com.example.temperatureapp.ui.Adapter.ConvertToAdapter
import com.example.temperatureapp.viewModel.UnitMesureVMFactory
import com.example.temperatureapp.viewModel.UnitMesureViewModel


class ConverterFragment : Fragment(R.layout.fragment_temperature) {


    // Initialize the ViewModel using viewModels and  a ViewModelFactory
    private val viewModel:UnitMesureViewModel by activityViewModels{
        UnitMesureVMFactory(
            MeasuresRepositoryImpl(
                MeasuresDataSource(DataStoreFactory.getStore(requireContext()))
            )
        )
    }



    // Variable to hold the reference to the binding view
    private lateinit var binding: FragmentTemperatureBinding
    private lateinit var adapterConvertFrom: ConvertFromAdapter
    private lateinit var adapterConvertTo: ConvertToAdapter




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTemperatureBinding.bind(view)




        // Update enter measure text
        binding.enterTxt.append(viewModel.getMeasureCategory().measureName)

        // Initialize the adapter and layout managers
        initializeAdapters()

        // Set up TextWatcher for the temperature input field to trigger conversions
        setupInputFieldWatcher()
                                                                        

    }


    // Initialize the adapter for 'Convert To' and 'Convert From' options
    private fun initializeAdapters() {
        // Create an instance of the ConvertFromAdapter and set its item click listener
        adapterConvertFrom = ConvertFromAdapter(viewModel.unitList, object : ConvertFromAdapter.onClickListener{
            override fun onItemClick(selected: MeasureUnits) {

                // Log the selected temperature unit
                Log.v(UnitMesureViewModel.TAG, selected.toString())

                // Trigger a temperature conversion with the selected unit and the current input value
                var currentInputValue = binding.inputTemperature.text

                if(!currentInputValue.isNullOrEmpty()) {
                    viewModel.refreshCalculation(selected, currentInputValue.toString().toFloat())
                }
            }
        })
        // Set the 'Convert From' adapter for the RecyclerView
        binding.rvConvertFrom.adapter = adapterConvertFrom
        // Set a LinearLayoutManager for the 'Convert From' RecyclerView
        binding.rvConvertFrom.layoutManager = LinearLayoutManager(context)


        // Initialize the adapter for the 'Convert To' options
        adapterConvertTo = ConvertToAdapter(viewModel.unitList, emptyList())
        binding.rvConvertTo.adapter = adapterConvertTo
        binding.rvConvertTo.layoutManager = LinearLayoutManager(context)


        // Observe the LiveData for calculation results and update the adapter when it changes
        viewModel.calculationResult.observe(viewLifecycleOwner) {updatedlist->
            adapterConvertTo.setList(updatedlist)
        }

    }


    // Set up a TextWatcher for the temperature input field
    private fun setupInputFieldWatcher() {
        binding.inputTemperature.addTextChangedListener(object :TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {


                // Check if the input is empty and show error number is required
                if (s.isNullOrEmpty() || s.toString().equals(".") ) {
                    binding.inputTemperature.setError("Number is required")
                } else if(!s.isNullOrEmpty()) {

                    // Check if the input is not empty and a conversion unit is selected
                    // Parse the input text to a Float and trigger a temperature conversion
                    viewModel.refreshCalculation(value = s.toString().toFloat())

                }
            }

        })
    }

}