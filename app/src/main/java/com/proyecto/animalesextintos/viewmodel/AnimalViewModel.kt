package com.proyecto.animalesextintos.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.proyecto.animalesextintos.data.AnimalDao
import com.proyecto.animalesextintos.model.Animal
import com.proyecto.animalesextintos.repository.AnimalRepository


class AnimalViewModel(application: Application) : AndroidViewModel(application) {

    val getAllData: MutableLiveData<List<Animal>>
    private val repository: AnimalRepository = AnimalRepository(AnimalDao())

    init {
        getAllData = repository.getAllData
    }

    fun addAnimal(animal: Animal) {
            repository.addAnimal(animal)
    }

    fun updateAnimal(animal: Animal) {
            repository.updateAnimal(animal)
    }

    fun deleteAnimal(animal: Animal) {
            repository.deleteAnimal(animal)
    }
}
