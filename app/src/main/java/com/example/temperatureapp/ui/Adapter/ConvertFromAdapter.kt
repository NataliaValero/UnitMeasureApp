package com.example.temperatureapp.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.temperatureapp.model.MeasureUnits

class ConvertFromAdapter(var measuresUnits: List<MeasureUnits>, val listener:  onClickListener) :  RecyclerView.Adapter<ConvertFromAdapter.MeasureViewHolder>() {



    inner class MeasureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private var selectedPosition = 0



    // Create a view holder for each in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeasureViewHolder {
        // inflate item_measure xml
        val view = LayoutInflater.from(parent.context).inflate(R.layout.convert_from_adapter, parent, false)

        //return a view holder
        return MeasureViewHolder(view)
    }


    // Return the number of items in the RecyclerView
    override fun getItemCount(): Int {
        return measuresUnits.size
    }

    // Bind data to each item in ther RecyclerView
    override fun onBindViewHolder(holder: MeasureViewHolder, position: Int) {
        val option = measuresUnits[position].measureName
        val currentPosition = position
        holder.itemView.apply {

            // Get references to UI elements
            var radioBtn = findViewById<RadioButton>(R.id.radioBtn)

            // Set text on the radio btn
            radioBtn.setText(option)

            //Check the selected radio button based on the current position
            radioBtn.isChecked = currentPosition ==  selectedPosition
        }


        // Set a click listener for the item
        holder.itemView.setOnClickListener {
            // Update the selected position
            selectedPosition = currentPosition

            // Trigger the item click listener and pass the selected position
            listener.onItemClick(measuresUnits[position])

            // Notify the adapter the the data has changed
            notifyDataSetChanged()
        }
    }


    // Interface to define the item click listener
    interface onClickListener {
        fun onItemClick(itemSelected:MeasureUnits)
    }
}