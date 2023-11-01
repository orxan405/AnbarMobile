package com.nexis.anbar.Adapter

import android.util.Log
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nexis.anbar.data.model.dataVerticalKino

class AdapterVertical(
    val list_V: ArrayList<dataVerticalKino>,
    val navigateToKinoEtrafli: (dataVerticalKino) -> Unit
) : RecyclerView.Adapter<VerticalKinoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalKinoViewHolder {
        return VerticalKinoViewHolder(parent)
    }

    override fun getItemCount() = list_V.size

    override fun onBindViewHolder(holder: VerticalKinoViewHolder, position: Int) {
        holder.bind(list_V[position], navigateToKinoEtrafli)

        Log.i("onBindViewHolder", list_V[position].toString())

//        holder.binding.btnVertikal.setOnClickListener {
//            holder.binding.btnVertikal.setBackgroundColor(Color.DKGRAY)
//
//            if (!holder.binding.txtVB.toString().isEmpty() && !holder.binding.txtVI.toString()
//                    .isEmpty()
//            ) {
//                val bundle = Bundle()
//                bundle.putString("data", holder.binding.txtVB.toString())
//
//                holder.binding.btnVertikal.text = holder.binding.txtVB.text.toString()
//            }
//        }


//
    }
}