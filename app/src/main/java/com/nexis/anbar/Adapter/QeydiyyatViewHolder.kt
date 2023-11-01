package com.nexis.anbar.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexis.anbar.data.model.dataVerticalKino
import com.nexis.anbar.data.model.tblQeydiyyat
import com.nexis.anbar.databinding.CardFilmVerticalBinding
import com.nexis.anbar.databinding.CardListQeydiyyatBinding

class QeydiyyatViewHolder(
    val parent: ViewGroup,
    val binding: CardListQeydiyyatBinding = CardListQeydiyyatBinding.inflate(
        LayoutInflater.from(
            parent.context
        ), parent, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dataQeydiyyat: tblQeydiyyat,
        navigateToQeydEtrafli: (tblQeydiyyat) -> Unit
    ) {
        binding.txtAdiQ.text = dataQeydiyyat.ad
        binding.txtSoyadiQ.text = dataQeydiyyat.soyad
        binding.txtAtaAdiQ.text = dataQeydiyyat.ata_ad
        binding.txtCinsiQ.text = dataQeydiyyat.cins
        binding.txtDTarixiQ.text = dataQeydiyyat.dogumT
        binding.txtEmailiQ.text = dataQeydiyyat.mail
        binding.txtNomresiQ.text = dataQeydiyyat.nomre
        binding.txtParoluQ.text = dataQeydiyyat.parolu
        binding.txtUserQ.text = dataQeydiyyat.istifade
//        Glide.with(parent.context).load(dataQeydiyyat.sekilVertical).centerCrop()
//            .into(binding.IVVertical)


//        binding.btnVertikal.setOnClickListener {
//            navigateToKinoEtrafli(dataQeydiyyat)
//        }



    }



//    val basliqVertical = itemView.findViewById<TextView>(R.id.txtVB)
//    val icerikVertical = itemView.findViewById<TextView>(R.id.txtVI)
//    val sekilVertical = itemView.findViewById<ImageView>(R.id.IVVertical)
//    val btnVertikal = itemView.findViewById<Button>(R.id.btnVertikal)
}