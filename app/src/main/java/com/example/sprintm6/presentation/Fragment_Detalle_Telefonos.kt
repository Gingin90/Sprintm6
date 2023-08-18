package com.example.sprintm6.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.ej5m6.R
import com.example.ej5m6.databinding.FragmentDetalleTelefonoBinding


private const val ARG_PARAM1 = "id"

class Fragment_Detalle_Telefonos : Fragment() {


    private val celVM: CelViewModel by activityViewModels()
    lateinit var binding: FragmentDetalleTelefonoBinding
    private var param1: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalleTelefonoBinding.inflate(layoutInflater,container,false)
        celVM.getDetalleTelefonoVM(param1)
        //cellVM.getDetalleTelefonoVM(param1).invokeOnCompletion {}
        celVM.detalleLiveData(param1).observe(viewLifecycleOwner){
            if(it != null) {
                binding.txtNombreDetalle.text = it.name
                binding.txtPrecioDetalle.text = "Ultimo precio: $" +  it.price.toString()
                binding.txtUltimoPrecioDetalle.text = "Precio Actual: $" +  it.lastPrice.toString()
                binding.txtDescripcionDetalle.text = it.description
                if (!it.credit){
                    binding.txtCredit.text = "SOLO EFECTIVO"
                }else{
                    binding.txtCredit.text = "ACEPTA CREDITO"
                }
                binding.imgDetalle.load(it.image)

                binding.floatingButton.setOnClickListener{

                }

                val textoAsunto = "Consulta: ${it.name} id: ${it.id}"
                val textoBody = "Hola \n  Vi la propiedad ${it.name} de código ${it.id} y me gustaría \n que me contactaran a este correo o al siguiente número:_____ \n Quedo atento."
                binding.editTextEmailAsunto.setText(textoAsunto)
                binding.editTextEmailBody.setText(textoBody)

                binding.floatingButton.setOnClickListener{

                    if(binding.cardViewEmail.visibility == View.GONE){
                        binding.cardViewEmail.visibility = View.VISIBLE
                    }else{
                        binding.cardViewEmail.visibility = View.GONE
                    }

                }
            }

        }

        //"buttonEmailMe" es el id del boton
        binding.buttonEmailMe!!.setOnClickListener {
            //mail cliente
            val destinatario = "info@novaera.cl"
            val intentEmail = Intent(Intent.ACTION_SEND, Uri.parse(destinatario))
            intentEmail.type = "plain/text"
            //Donde llegan
            intentEmail.putExtra(Intent.EXTRA_EMAIL,arrayOf(destinatario))

            //Titulo Mail
            intentEmail.putExtra(Intent.EXTRA_SUBJECT, binding.editTextEmailAsunto.text.toString())
            //Body Mail
            intentEmail.putExtra(Intent.EXTRA_TEXT, binding.editTextEmailBody.text.toString())

            startActivity(Intent.createChooser(intentEmail, "Consulta producto"))

            findNavController().navigate(R.id.action_fragment_Detalle_Telefono_to_fragment_ListadoCelulares)

        }

        return binding.root
    }


}