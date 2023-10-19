package com.example.temperatureapp.ui


import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.temperatureapp.data.DataStoreFactory
import com.example.temperatureapp.data.MeasuresDataSource
import com.example.temperatureapp.repository.MeasuresRepositoryImpl
import com.example.temperatureapp.viewModel.UnitMesureVMFactory
import com.example.temperatureapp.viewModel.UnitMesureViewModel

class MainActivity : AppCompatActivity(){

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}



