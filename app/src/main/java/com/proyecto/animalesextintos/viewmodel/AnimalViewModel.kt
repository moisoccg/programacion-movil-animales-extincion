package com.proyecto.animalesextintos.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.proyecto.animalesextintos.data.AnimalDatabase
import com.proyecto.animalesextintos.model.Animal
import com.proyecto.animalesextintos.repository.AnimalRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AnimalViewModel(application: Application) : AndroidViewModel(application) {

    val getAllData: LiveData<List<Animal>>
    private val repository: AnimalRepository

    init {
        val animalDao = AnimalDatabase.getDatabase(application).animalDao()
        repository = AnimalRepository(animalDao)
        getAllData = repository.getAllData
    }

    fun addAnimal(animal: Animal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAnimal(animal)
        }
    }

    fun updateAnimal(animal: Animal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateAnimal(animal)
        }
    }

    fun deleteAnimal(animal: Animal) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAnimal(animal)
        }
    }
}
