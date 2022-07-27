package com.proyecto.animalesextintos.data

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.proyecto.animalesextintos.model.Animal

@Database(entities = [Animal::class], version=1, exportSchema = false)
abstract class AnimalDatabase: RoomDatabase() {
    abstract fun animalDao(): AnimalDao

    companion object {
        @Volatile
        private var INSTANCE: AnimalDatabase? = null

        fun getDatabase(context: android.content.Context): AnimalDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AnimalDatabase::class.java,
                    "animal_database"
                ).build()
                INSTANCE=instance
                return instance
            }
        }
    }
}