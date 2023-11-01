package com.nexis.anbar.Adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexis.anbar.R


class HorizontalKinoViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val basliqHorizontal = itemView.findViewById<TextView>(R.id.txtHB)
    val sekilHorizontal = itemView.findViewById<ImageView>(R.id.IVHorizontal)

}