package com.proyecto.animalesextintos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.proyecto.animalesextintos.databinding.AnimalFilaBinding
import com.proyecto.animalesextintos.model.Animal
import com.proyecto.animalesextintos.ui.animal.AnimalFragmentDirections


class AnimalAdapter : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    //Lista para almacenar informacio
    private var listaAnimales = emptyList<Animal>()

    inner class AnimalViewHolder(private val itemBinding: AnimalFilaBinding) :
        RecyclerView.ViewHolder(itemBinding.root){
        fun bind(animal: Animal){
            itemBinding.animalName.text = animal.nombre
            //Para navegar al update
            itemBinding.vistaFila.setOnClickListener{
                val action = AnimalFragmentDirections.actionNavAnimalToUpdateAnimalFragment(animal)
                itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalAdapter.AnimalViewHolder {
        val itemBinding = AnimalFilaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimalViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animalActual = listaAnimales[position]
        holder.bind(animalActual)
    }

    override fun getItemCount(): Int {
        return listaAnimales.size
    }

    fun setData(animales: List<Animal>){
        this.listaAnimales = animales
        notifyDataSetChanged()
    }
}