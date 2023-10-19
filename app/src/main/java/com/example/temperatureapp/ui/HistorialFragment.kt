package com.example.temperatureapp.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentHistorialBinding
import com.example.temperatureapp.data.DataStoreFactory
import com.example.temperatureapp.data.MeasuresDataSource
import com.example.temperatureapp.model.MeasureType
import com.example.temperatureapp.repository.MeasuresRepositoryImpl
import com.example.temperatureapp.ui.Adapter.MeasureAdapterHistorial
import com.example.temperatureapp.viewModel.UnitMesureVMFactory
import com.example.temperatureapp.viewModel.UnitMesureViewModel


class HistorialFragment : Fragment(R.layout.fragment_historial) {


    private lateinit var binding: FragmentHistorialBinding
    private var measureChoice:String = ""


    private val viewModel by viewModels<UnitMesureViewModel> {
        UnitMesureVMFactory(
            MeasuresRepositoryImpl(
                MeasuresDataSource(DataStoreFactory.getStore(requireContext()))
            )
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistorialBinding.bind(view)


        val temperatures = resources.getStringArray(R.array.measureOptions)
        val arrayAdapter = ArrayAdapter(
            requireContext(),
            R.layout.dropdown_item,
            temperatures
        )
        binding.autoCompleteTV.setAdapter(arrayAdapter)

        binding.autoCompleteTV.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                measureChoice = parent?.getItemAtPosition(position).toString()

                val measureType = when(measureChoice){
                    "Temperatures" -> MeasureType.TEMPERATURE
                    "Weights"-> MeasureType.WEIGHT
                    else -> MeasureType.WEIGHT
                }

                viewModel.getMeasuresLiveData(measureType)
                    .observe(viewLifecycleOwner) {list->
                        var adapter = MeasureAdapterHistorial(list)
                        binding.rvMeasureList.adapter = adapter
                        binding.rvMeasureList.layoutManager = LinearLayoutManager(context)

                    }
            }
        }

        // Clear historial

        binding.btnClearAll.setOnClickListener{

            if(measureChoice.equals("Temperatures")) {
                viewModel.clearMeasuresLiveData(MeasureType.TEMPERATURE)
            } else if (measureChoice.equals("Weights")){
                viewModel.clearMeasuresLiveData(MeasureType.WEIGHT)
            }

        }
    }
}