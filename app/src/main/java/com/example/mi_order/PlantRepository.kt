package com.example.mi_order

import android.net.Uri
import com.example.mi_order.PlantRepository.Singleton.databaseRef
import com.example.mi_order.PlantRepository.Singleton.downloadUri
import com.example.mi_order.PlantRepository.Singleton.plantList
import com.example.mi_order.PlantRepository.Singleton.storageReference
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import java.util.*

class PlantRepository {

    // On fait un singleton pour éviter de recreer un arraylistof à chaque nouvel objet
    object Singleton {
        //Donner le lien pour acceder au bucket
        private val BUCKET_URL: String = "gs://naturecollection-2d41c.appspot.com"

        //On veut se connecter à notre espace de stockage
        val storageReference = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)

        // se connecter à la référence plante
        val databaseRef = FirebaseDatabase.getInstance().getReference("plants")

        // créer une liste qui va contenir nos plantes
        val plantList = arrayListOf<PlantModel>()

        //contenir le lien de l'image courante
        var downloadUri: Uri? = null

    }

    fun updateData(callback: () -> Unit){ //le callback unit permet d'attendre que les informations chargent avant de vouloir les montrer (avec le *)
        // absorber les données depuis la databaseReference pour les donner à notre liste de plante
        databaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) { //Il recupere une liste d'objet qui n'est pas encore un PlantModel
                //retirer les anciennes
                plantList.clear()
                //recolter la liste
                for (ds in snapshot.children) {//ds c'est le nom générique de DataSnapshot
                    //Construire un objet plante
                    val plant = ds.getValue(PlantModel::class.java)

                    // verifier que la class est pas nulle
                    if(plant != null){
                        //ajouter la plante à notre liste
                        plantList.add(plant)
                    }
                }
                // * actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {
                //Au cas où il trouve pas les éléments en question
            }

        })
    }
    //On va créer une fonciton pour envoyer des fichiers sur le storage
    fun uploadImage(file: Uri, callback: () -> Unit){
        //Vérifier que ce fichier n'est pas null
        if(file != null) {
            val fileName = UUID.randomUUID().toString() + ".jpg" //On creer le nom du fichier
            val ref = storageReference.child(fileName) //On dit où dans la bdd on doit le mettre
            val uploadTask = ref.putFile(file) //On lui associe quel est le contenu à soumettre

            //Démarrer la tâche d'envoi
            uploadTask.continueWithTask(Continuation<UploadTask.TaskSnapshot, Task<Uri>> { task ->

                // on vérifie s'il y a eu un pb lors de l'envoi du fichier
                if (!task.isSuccessful){
                    task.exception?.let { throw it }
                }

                return@Continuation ref.downloadUrl

            }).addOnCompleteListener { task ->
                //Verifier si tout à bien fonctionné
                if(task.isSuccessful) {
                    //recuperer l'image
                    downloadUri = task.result
                    callback()
                }
            }
        }
    }

    // Mettre à jour un objet plant dans le BDD
    fun updatePlant(plant: PlantModel){
        databaseRef.child(plant.id).setValue(plant)
    }
    //Inserer une nouvelle plante en bdd
    fun insertPlant(plant: PlantModel) = databaseRef.child(plant.id).setValue(plant)

    // Supprimer une plante de la base
    fun deletePlant(plant: PlantModel) = databaseRef.child(plant.id).removeValue()


}


