package com.nexis.anbar.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexis.anbar.data.model.dataHorizontalKino
import com.nexis.anbar.R

class AdapterHorizontal(val list: ArrayList<dataHorizontalKino>, val context: Context) :
    RecyclerView.Adapter<HorizontalKinoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalKinoViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.card_film_horizontal, parent, false)

        return HorizontalKinoViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HorizontalKinoViewHolder, position: Int) {
        Log.i("onBindViewHolder", list[position].toString())
        holder.basliqHorizontal.text = list[position].basliqHorizontal

        Glide.with(context).load(list[position].sekilHorizontal).centerCrop()
            .into(holder.sekilHorizontal)
    }
}