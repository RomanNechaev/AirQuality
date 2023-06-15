package com.example.airquality.screens.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.airquality.R
import com.example.airquality.models.AirQuality
import kotlinx.android.synthetic.main.item_layout.view.item_air_name
import kotlinx.android.synthetic.main.item_layout.view.item_air_value
import kotlinx.android.synthetic.main.item_layout.view.item_title

class MainAdapter: RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    private var listSaved = emptyList<AirQuality>()


    class MyViewHolder(view: View):RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return MyViewHolder(view)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.item_title.text = listSaved[position].placeId
        holder.itemView.item_air_name.text = listSaved[position].level
        holder.itemView.item_air_value.text = listSaved[position].value
    }

    override fun getItemCount(): Int {
        return listSaved.size
    }

}