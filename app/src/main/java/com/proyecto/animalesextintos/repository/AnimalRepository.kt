package com.proyecto.animalesextintos.repository

import androidx.lifecycle.LiveData
import com.proyecto.animalesextintos.data.AnimalDao
import com.proyecto.animalesextintos.model.Animal

class AnimalRepository(private val animalDao: AnimalDao) {

    val getAllData: LiveData<List<Animal>>  = animalDao.getAll()
    suspend fun addAnimal(animal: Animal){
        animalDao.addAnimal(animal)
    }

    suspend fun updateAnimal(animal: Animal){
        animalDao.updateAnimal(animal)
    }

    suspend fun deleteAnimal(animal: Animal){
        animalDao.deleteAnimal(animal)
    }
}