package com.example.temperatureapp.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.temperatureapp.model.MeasureModel

class MeasureAdapterHistorial(var measures: List<MeasureModel>) : RecyclerView.Adapter<MeasureAdapterHistorial.MeasureViewHolder>(){

    inner class MeasureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasureViewHolder {
        // inflate item_measure xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_measure_historial, parent, false)

        //return a view holder
        return MeasureViewHolder(view)
    }

    override fun getItemCount(): Int {
        return measures.size
    }

    override fun onBindViewHolder(holder: MeasureViewHolder, position: Int) {
        holder.itemView.apply {

            var quantity = findViewById<TextView>(R.id.tvQuantity)
            var measure = findViewById<TextView>(R.id.tvMeasure)

            quantity.text = measures[position].measure.toString()
            measure.text = measures[position].convertFrom.toString()

        }
    }
}