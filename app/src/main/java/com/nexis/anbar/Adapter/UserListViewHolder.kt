package com.nexis.anbar.Adapter

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexis.anbar.R

class UserListViewHolder(val view: View): RecyclerView.ViewHolder(view) {
    val basliq = itemView.findViewById<TextView>(R.id.txtBasliq)
    val icerik = itemView.findViewById<TextView>(R.id.txtIcerik)
    val mainLayout = itemView.findViewById<LinearLayout>(R.id.mainLayout)
    val mainImage = itemView.findViewById<ImageView>(R.id.UserSekil)
}