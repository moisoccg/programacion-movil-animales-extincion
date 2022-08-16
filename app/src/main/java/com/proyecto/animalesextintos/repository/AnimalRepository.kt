package com.proyecto.animalesextintos.repository

import androidx.lifecycle.MutableLiveData
import com.proyecto.animalesextintos.data.AnimalDao
import com.proyecto.animalesextintos.model.Animal

class AnimalRepository(private val animalDao: AnimalDao) {

    val getAllData: MutableLiveData<List<Animal>>  = animalDao.getAll()
     fun addAnimal(animal: Animal){
        animalDao.addAnimal(animal)
    }

     fun updateAnimal(animal: Animal){
        animalDao.updateAnimal(animal)
    }

     fun deleteAnimal(animal: Animal){
        animalDao.deleteAnimal(animal)
    }
}