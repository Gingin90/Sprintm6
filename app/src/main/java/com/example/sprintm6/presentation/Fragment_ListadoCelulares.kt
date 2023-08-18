package com.example.sprintm6.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.ej5m6.databinding.FragmentListadoCelularesBinding

class Fragment_ListadoCelulares : Fragment() {

    lateinit var binding: FragmentListadoCelularesBinding
    private val cellVM: CelViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding = FragmentListadoCelularesBinding.inflate(layoutInflater,container,false)

        initAdapter()
        cellVM.getAllTelefonos()

        return binding.root
    }

    private fun initAdapter() {
        val adapter = AdapterCell()
        binding.recyclerListado.adapter = adapter
        cellVM.celularLiveData().observe(viewLifecycleOwner){
            adapter.setData(it)
        }
    }

}