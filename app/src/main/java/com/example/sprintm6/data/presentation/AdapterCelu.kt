package com.example.Sprintm6.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.Sprintm6.data.local.CeluEntity
import com.example.ej5m6.R
import com.example.ej5m6.databinding.ItemCeluBinding

class AdapterCelu() : RecyclerView.Adapter<AdapterCelu.ItemCeluViewHolder>() {

    lateinit var binding: ItemCeluBinding
  val listItemCelu = mutableListOf<CeluEntity>()


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterCelu.ItemCeluViewHolder {
        binding = ItemCeluBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemCeluViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ItemCeluViewHolder, position: Int) {
        val celu= listItemCelu[position]
        holder.bind(celu)
    }



    override fun getItemCount(): Int {
        return listItemCelu.size
    }

    fun setData(celus: List<CeluEntity>) {
        this.listItemCelu.clear()
        this.listItemCelu.addAll(celus)
        notifyDataSetChanged()


    }


    class ItemCeluViewHolder(val celusVistas: ItemCeluBinding) :
        RecyclerView.ViewHolder(celusVistas.root) {

        fun bind(celu: CeluEntity) {

            celusVistas.txtRaza.text = celu.celu
            celusVistas.cardViewRazas.setOnClickListener {
                val bundle = Bundle()
                bundle.putString("id", celu.celu)

                Navigation.findNavController(celusVistas.root)
                    .navigate(R.id.action_fragmentListado_to_fragmentDetalle,bundle)
            }


        }


    }

    class ItemCeluViewHolder {
        fun bind(celu: CeluEntity) {


        }
    }
}
