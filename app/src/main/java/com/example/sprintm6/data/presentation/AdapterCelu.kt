package com.example.ind8m6.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.ej5m6.R
import com.example.ej5m6.databinding.ItemRazasBinding
import com.example.ind8m6.data.local.RazaEntity

class AdapterCelu : RecyclerView.Adapter<AdapterCelu.ItemRazasViewHolder>() {

    lateinit var binding: ItemRazasBinding
  val listItemRazas = mutableListOf<RazaEntity>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterCelu.ItemRazasViewHolder {
        binding = ItemRazasBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemRazasViewHolder(binding)

    }

    override fun onBindViewHolder(holder: AdapterCelu.ItemRazasViewHolder, position: Int) {
        val raza = listItemRazas[position]
        holder.bind(raza)
    }

    override fun getItemCount(): Int {
        return listItemRazas.size
    }

    fun setData(razas: List<RazaEntity>) {
        this.listItemRazas.clear()
        this.listItemRazas.addAll(razas)
        notifyDataSetChanged()


    }


    class ItemRazasViewHolder(val razasVistas: ItemRazasBinding) :
        RecyclerView.ViewHolder(razasVistas.root) {

        fun bind(raza: RazaEntity) {

            razasVistas.txtRaza.text = raza.raza
            razasVistas.cardViewRazas.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("id", raza.raza)

                Navigation.findNavController(razasVistas.root)
                    .navigate(R.id.action_listadoRazas_to_fragmentDetalle, bundle)
            }


        }


    }
}
