package com.example.temperatureapp.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.temperatureapp.model.MeasureUnits

class ConvertToAdapter(var measuresUnits: List<MeasureUnits>, var valuesConverted: List<Float>): RecyclerView.Adapter<ConvertToAdapter.ConvertToViewHolder>() {
    inner class ConvertToViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    // Create a vire holder for each item in the RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConvertToViewHolder {
        // inflate item_measure.xml layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.convert_to_adapter, parent, false)

        // Return a view holder
        return ConvertToViewHolder(view)
    }

    // Return the number of items in the RecyclerView
    override fun getItemCount(): Int {
        return measuresUnits.size
    }

    // Bind data to each item in the RecyclerView
    override fun onBindViewHolder(holder: ConvertToViewHolder, position: Int) {
        holder.itemView.apply {

            // Get references to UI elements
            var convertTo = findViewById<TextView>(R.id.rvConvertToTv)
            var convertToTxt = findViewById<TextView>(R.id.rvConvertToTextTv)


            // Set the text on the TextViews with values from the provided lists
            convertTo.text = valuesConverted[position].toString()
            convertToTxt.text = measuresUnits[position].measureSymbol


        }
    }

    // Update the data and notify the adapter of the changes
    fun setList(newList: List<Float>) {
        valuesConverted = newList
        notifyDataSetChanged()
    }

}
