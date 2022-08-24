package com.proyecto.animalesextintos.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.ktx.Firebase
import com.proyecto.animalesextintos.model.Animal

class AnimalDao {

    private var codigoUsuario: String
    private var firestore: FirebaseFirestore
    private var animalesapp = "AnimalesApp"
    private var miColeccion = "misAnimales"

init {
    val usuario = Firebase.auth.currentUser?.email
    codigoUsuario= "$usuario"

    firestore = FirebaseFirestore.getInstance()
    firestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()

}

    fun getAll(): MutableLiveData<List<Animal>> {
        val listaAnimales = MutableLiveData<List<Animal>>()

        firestore.collection(animalesapp)
            .document(codigoUsuario)
            .collection(miColeccion)
            .addSnapshotListener{snapshot, e->
                if (e != null) {
                    return@addSnapshotListener
                }
                if (snapshot != null) {
                    val lista = ArrayList<Animal>()
                    val Animales = snapshot.documents

                    Animales.forEach{
                       val animal = it.toObject(Animal::class.java)
                        if (animal != null){
                            lista.add(animal)
                        }
                    }
                    listaAnimales.value = lista
                }
            }
        return  listaAnimales
    }

    fun addAnimal(animal: Animal) {
        var document: DocumentReference
        if (animal.id.isEmpty()) {
            document = firestore
                .collection(animalesapp)
                .document(codigoUsuario)
                .collection(miColeccion)
                .document()
            animal.id = document.id
        }else{
            document = firestore
                .collection(animalesapp)
                .document(codigoUsuario)
                .collection(miColeccion)
                .document(animal.id)
        }
        val set = document.set(animal)
        set.addOnSuccessListener {
            Log.d("AddAnimal", "Animal Agregado" + animal.id)
        }
            .addOnCanceledListener {
                Log.d("AddAnimal", "Animal NO Agregado" + animal.id)
            }
    }

     fun updateAnimal(animal: Animal){
         addAnimal(animal)

     }

     fun deleteAnimal(animal: Animal){
         if (animal.id.isNotEmpty()){
             firestore
                 .collection(animalesapp)
                 .document(codigoUsuario)
                 .collection(miColeccion)
                 .document(animal.id)
                 .delete()
                 .addOnSuccessListener {
                 Log.d("deleteAnimal", "Animal eliminado" + animal.id)
             }
                 .addOnCanceledListener {
                     Log.d("deleteAnimal", "Animal NO eliminado" + animal.id)
                 }
         }
     }
}