package com.nexis.anbar.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexis.anbar.R
import com.nexis.anbar.data.model.UserModel

class UserListAdapter(val list: ArrayList<UserModel>, val context: Context) : RecyclerView.Adapter<UserListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {

        val view: View =
            LayoutInflater.from(context).inflate(R.layout.adapter_item_user, parent, false)

        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        Log.i("onBindViewHolder", list[position].toString() )
        holder.basliq.text = list[position].basliq
        holder.icerik.text = list[position].icerik

        Glide.with(context).load(list[position].sekil).centerCrop().into(holder.mainImage)

        if(list[position].basliq=="Proqramlasdirma"){
            holder.mainLayout.setBackgroundColor(context.getColor(R.color.BG))
        }
        else{
            holder.mainLayout.setBackgroundColor(context.getColor(R.color.LinearBG))
        }
    }
}