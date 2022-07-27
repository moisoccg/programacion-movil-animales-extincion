package com.proyecto.animalesextintos.data

import android.widget.RadioGroup
import androidx.lifecycle.LiveData
import androidx.room.*
import com.proyecto.animalesextintos.model.Animal

@Dao
interface AnimalDao {

    @Query("SELECT * FROM ANIMAL")
    fun getAll(): LiveData<List<Animal>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAnimal(animal: Animal)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun updateAnimal(animal: Animal)

    @Delete
    suspend fun deleteAnimal(animal: Animal)
}