package com.proyecto.animalesextintos.ui.animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyecto.animalesextintos.R
import com.proyecto.animalesextintos.adapter.AnimalAdapter
import com.proyecto.animalesextintos.databinding.FragmentAddAnimalBinding
import com.proyecto.animalesextintos.databinding.FragmentAnimalBinding
import com.proyecto.animalesextintos.model.Animal
import com.proyecto.animalesextintos.viewmodel.AnimalViewModel


class AnimalFragment : Fragment() {

    private var _binding: FragmentAnimalBinding? = null
    private lateinit var animalViewModel: AnimalViewModel
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        animalViewModel =
            ViewModelProvider(this).get(AnimalViewModel::class.java)

        _binding = FragmentAnimalBinding.inflate(inflater, container, false)

        //binds to fragment_animal.xml

        binding.btnAddAnimal.setOnClickListener {
            findNavController().navigate(R.id.action_nav_animal_to_addAnimalFragment) //take meto  fragment
        }

        //Activar recycler view
        val animalAdapter = AnimalAdapter()
        val recycler = binding.recycler
        recycler.adapter = animalAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())

        animalViewModel = ViewModelProvider(this)[AnimalViewModel::class.java]
        animalViewModel.getAllData.observe(viewLifecycleOwner){
                animales -> animalAdapter.setData(animales)
        }

        return  binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}