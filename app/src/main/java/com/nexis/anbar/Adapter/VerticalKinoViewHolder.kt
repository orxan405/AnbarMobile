package com.nexis.anbar.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nexis.anbar.data.model.dataVerticalKino
import com.nexis.anbar.databinding.CardFilmVerticalBinding

class VerticalKinoViewHolder(
    val parent: ViewGroup,
    val binding: CardFilmVerticalBinding = CardFilmVerticalBinding.inflate(
        LayoutInflater.from(
            parent.context
        ), parent, false
    )
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        dataVerticalKino: dataVerticalKino,
        navigateToKinoEtrafli: (dataVerticalKino) -> Unit
    ) {
        binding.txtVB.text = dataVerticalKino.basliqVertical
        binding.txtVI.text = dataVerticalKino.icerikVertical
        Glide.with(parent.context).load(dataVerticalKino.sekilVertical).centerCrop()
            .into(binding.IVVertical)


        binding.btnVertikal.setOnClickListener {
            navigateToKinoEtrafli(dataVerticalKino)
        }



    }



//    val basliqVertical = itemView.findViewById<TextView>(R.id.txtVB)
//    val icerikVertical = itemView.findViewById<TextView>(R.id.txtVI)
//    val sekilVertical = itemView.findViewById<ImageView>(R.id.IVVertical)
//    val btnVertikal = itemView.findViewById<Button>(R.id.btnVertikal)
}