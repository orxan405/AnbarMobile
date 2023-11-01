package com.nexis.anbar.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nexis.anbar.R
import com.nexis.anbar.data.model.tblQeydiyyat

class MyAdapter(val userList: ArrayList<tblQeydiyyat>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_list_qeydiyyat, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]


        holder.adi.text = currentitem.ad
        holder.soyadi.text = currentitem.soyad
        holder.ata_adi.text = currentitem.ata_ad
        holder.cinsi.text = currentitem.cins
        holder.dogumTarixi.text = currentitem.dogumT
        holder.istifadeciAdi.text = currentitem.istifade
        holder.parolu.text = currentitem.parolu
        holder.emaili.text = currentitem.mail
        holder.nomresi.text = currentitem.nomre
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val adi: TextView = itemView.findViewById(R.id.txtAdiQ)
        val soyadi: TextView = itemView.findViewById(R.id.txtSoyadiQ)
        val ata_adi: TextView = itemView.findViewById(R.id.txtAtaAdiQ)
        val cinsi: TextView = itemView.findViewById(R.id.txtCinsiQ)
        val dogumTarixi: TextView = itemView.findViewById(R.id.txtDTarixiQ)
        val istifadeciAdi: TextView = itemView.findViewById(R.id.txtUserQ)
        val parolu: TextView = itemView.findViewById(R.id.txtParoluQ)
        val emaili: TextView = itemView.findViewById(R.id.txtEmailiQ)
        val nomresi: TextView = itemView.findViewById(R.id.txtNomresiQ)
    }
}