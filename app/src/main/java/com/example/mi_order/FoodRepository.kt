package com.example.mi_order

import android.net.Uri
import com.example.mi_order.FoodRepository.Singleton.databaseRef
import com.example.mi_order.FoodRepository.Singleton.foodList
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class FoodRepository {


    // On fait un singleton pour éviter de recreer un arraylistof à chaque nouvel objet
    object Singleton {
        // se connecter à la référence

        val databaseRef = FirebaseDatabase.getInstance("https://mi-order-ad764-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Foods")

        // créer une liste qui va contenir nos plantes
        val foodList = arrayListOf<FoodModel>()

    }

    fun updateData(callback: () -> Unit) {
        //le callback unit permet d'attendre que les informations chargent avant de vouloir les montrer
        // absorber les données depuis la databaseReference pour les donner à notre liste de food
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) { //Il recupere une liste d'objet qui n'est pas encore un FoodModel
                //retirer les anciennes
                foodList.clear()
                //recolter la liste
                for (ds in snapshot.children) {//ds c'est le nom générique de DataSnapshot
                    //Construire un objet food
                    val food = ds.getValue(FoodModel::class.java)

                    // verifier que la class est pas nulle
                    if (food != null) {
                        //ajouter la plante à notre liste
                        foodList.add(food)
                    }
                }
                // Actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                //Au cas où il trouve pas les éléments en question
            }

        })
    }
}

