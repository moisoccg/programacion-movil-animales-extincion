package com.proyecto.animalesextintos.ui.animal

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.proyecto.animalesextintos.R
import com.proyecto.animalesextintos.databinding.FragmentUpdateAnimalBinding
import com.proyecto.animalesextintos.model.Animal
import com.proyecto.animalesextintos.viewmodel.AnimalViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [UpdateAnimalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateAnimalFragment : Fragment() {
    private var _binding: FragmentUpdateAnimalBinding? = null
    private val binding get() = _binding!!
    private lateinit var animalViewModel: AnimalViewModel

    private val args by navArgs<UpdateAnimalFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateAnimalBinding.inflate(inflater,container,false)

        animalViewModel = ViewModelProvider(this).get(AnimalViewModel::class.java)

        binding.btnUpdate.setOnClickListener {
            updateAnimal()
        }

        //binding.tvAltura.text=args.animal.longitud.toString();
        binding.tvAltura.text="1250 pies"
        binding.tvLatitud.text="10°01'08.9N"
        binding.tvLongitud.text="84°12'44.3W"

        binding.btnEmail.setOnClickListener{
            escribirCorreo()
        }

        binding.btnPhone.setOnClickListener{
            llamarAnimal()
        }

        binding.btnSms.setOnClickListener{
            enviarWhatsApp()
        }

        binding.btnInternet.setOnClickListener{
            verInternet()
        }

        binding.btnLocation.setOnClickListener{
            verMapa()
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteAnimal()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateAnimal(){
        val nombre = binding.animalName.text.toString()

        if(validation(nombre)){
            //guardar animal
            val animal = Animal(args.animal.id,nombre)
            animalViewModel.updateAnimal(animal) //enviando a la bd
            Toast.makeText(requireContext(), getString(R.string.msg_success), Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_updateAnimalFragment_to_nav_animal)
        } else{
            Toast.makeText(requireContext(), getString(R.string.msg_error), Toast.LENGTH_LONG).show()
        }
    }

    private fun validation(nombre: String): Boolean {
        return !(nombre.isEmpty())
    }

    private fun deleteAnimal(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.yes)) {
                _,_ -> animalViewModel.deleteAnimal(args.animal)
            Toast.makeText(requireContext(), getString(R.string.delete_success) +"a ${args.animal.nombre}!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateAnimalFragment_to_nav_animal)
        }
        builder.setNegativeButton(getString(R.string.no)) { _,_ ->}
        builder.setTitle(R.string.delete_success)
        builder.setMessage(getString(R.string.safeToDelete) + " ${args.animal.nombre}?")
        builder.create().show()
    }

    private fun llamarAnimal(){

    }

    private fun escribirCorreo(){
        //val para = binding.email.text.toString()
        val para = "moisoccg@gmail.com"

        if(para.isNotEmpty()){
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "message/rfc822"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(para))
            intent.putExtra(
                Intent.EXTRA_SUBJECT, getString(R.string.msg_saludos) + "" + binding.animalName.text
            )
            intent.putExtra(
                Intent.EXTRA_EMAIL,getString(R.string.msg_correo)
            )
            startActivity(intent)
        } else{
            Toast.makeText(requireContext(), getString(R.string.msg_error), Toast.LENGTH_SHORT)
        }
    }

    private fun enviarWhatsApp(){

    }

    private fun verMapa(){

    }

    private fun verInternet(){

    }
}