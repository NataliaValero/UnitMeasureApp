package com.example.temperatureapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMainBinding
import com.example.temperatureapp.data.DataStoreFactory
import com.example.temperatureapp.data.MeasuresDataSource
import com.example.temperatureapp.model.MeasureCategory
import com.example.temperatureapp.repository.MeasuresRepositoryImpl
import com.example.temperatureapp.viewModel.UnitMesureVMFactory
import com.example.temperatureapp.viewModel.UnitMesureViewModel


class MainFragment : Fragment(R.layout.fragment_main) {


    private lateinit var binding: FragmentMainBinding

    // Initialize the ViewModel using viewModels and  a ViewModelFactory
    private val viewModel:UnitMesureViewModel by activityViewModels{
        UnitMesureVMFactory(
            MeasuresRepositoryImpl(
                MeasuresDataSource(DataStoreFactory.getStore(requireContext()))
            )
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding.temperatureBtn.setOnClickListener {
            startFragment(view, MeasureCategory.TEMPERATURE)
        }

        binding.weightBtn.setOnClickListener {
            startFragment(view, MeasureCategory.WEIGHT)
        }

        binding.lengthBtn.setOnClickListener {
            startFragment(view, MeasureCategory.LENGTH)
        }


        binding.historialBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_mainFragment_to_historialFragment)
        }


    }

    fun startFragment(view: View, measureCategory: MeasureCategory) {
        viewModel.setMeasureCategory(measureCategory)
        view.findNavController().navigate(R.id.action_mainFragment_to_temperatureFragment)
    }
}