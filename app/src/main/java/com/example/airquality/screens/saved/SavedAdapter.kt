package com.example.airquality.screens.saved

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airquality.R
import com.example.airquality.models.AirQuality
import kotlinx.android.synthetic.main.item_layout.view.item_air_name
import kotlinx.android.synthetic.main.item_layout.view.item_air_value
import kotlinx.android.synthetic.main.item_layout.view.item_title

class SavedAdapter : RecyclerView.Adapter<SavedAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
    private var listAirQuality = emptyList<AirQuality>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listAirQuality.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.item_title.text = listAirQuality[position].city
        holder.itemView.item_air_value.text = listAirQuality[position].value
        holder.itemView.item_air_name.text = listAirQuality[position].level
    }

    fun setList(list: List<AirQuality>){
        listAirQuality = list
        notifyDataSetChanged()
    }


    override fun onViewDetachedFromWindow(holder: MyViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}