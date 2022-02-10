package com.example.mi_order.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.mi_order.*
import com.example.mi_order.PlantRepository.Singleton.downloadUri
import java.util.*

class AddPlantFragment(
    private val context: HomePage
) : Fragment(){
    //var change, val ne change pas
    private var uploadedImage: ImageView? = null
    private var file: Uri? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_add_plant, container,false )

        //Recup uploadedimage pour lui associer son composant
        uploadedImage = view.findViewById(R.id.preview_image)

        //recuper le bouton pour charger l'image
        val pickupImageButton = view.findViewById<Button>(R.id.upload_button)

        //Lorsqu'on clique dessus on ouvre les images du téléphone
        pickupImageButton.setOnClickListener{ pickupImage()}

        //recuper le bouton confirmer
        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener{ sendForm(view)}

        return view
    }

    private fun sendForm(view: View) {
        val repo = PlantRepository()
        repo.uploadImage(file!!){
            val plantName = view.findViewById<EditText>(R.id.name_input).text.toString()
            val plantDescription = view.findViewById<EditText>(R.id.description_input).text.toString()
            val grow = view.findViewById<Spinner>(R.id.grow_spinner).selectedItem.toString()
            val water = view.findViewById<Spinner>(R.id.water_spinner).selectedItem.toString()
            val downloadImageUrl = downloadUri

            //Créer un nouvel objet de type PlantModel pour l'envoyer ensuite en BDD
            val plant = PlantModel(
                UUID.randomUUID().toString(),
                plantName,
                plantDescription,
                downloadImageUrl.toString(),
                grow,
                water
            )
            //Envoyer en bdd
            repo.insertPlant(plant)
        }




    }

    //Créer la fonction pour récup image
    private fun pickupImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), 47)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 47 && resultCode == Activity.RESULT_OK){

            //Vérifier si les données sont nulles
            if(data == null || data.data == null) return

            // Si valide, on recup l'image selectionner
            file = data.data

            // Mettre à jour l'aperçu de l'image
            uploadedImage?.setImageURI(file)

        }
    }
}