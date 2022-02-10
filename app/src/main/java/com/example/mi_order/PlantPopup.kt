package com.example.mi_order

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.example.mi_order.adapter.PlantAdapter
import com.bumptech.glide.Glide

class PlantPopup(
    private val adapter: PlantAdapter,
    private val currentPlant: PlantModel,
    ) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_plant_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupStarButton()
    }
    private fun updateStar(button: ImageView){
        if (currentPlant.liked){
            button.setImageResource(R.drawable.ic_star)
        }
        else{
            button.setImageResource(R.drawable.ic_unstar)
        }
    }

    private fun setupStarButton() {
        //recuper
        val starButton = findViewById<ImageView>(R.id.star_button)
        updateStar(starButton)

        //interaction
        starButton.setOnClickListener{
            currentPlant.liked = !currentPlant.liked
            val repo = PlantRepository()
            repo.updatePlant(currentPlant)
            updateStar(starButton)
        }
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener{
        //Supprimer la plante de la bdd
            val repo = PlantRepository()
            repo.deletePlant(currentPlant)
            dismiss()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener{
            //fermer la fenetre
            dismiss()
        }
    }

    private fun setupComponents() {
        // actualiser l'image de la plante
        val plantImage = findViewById<ImageView>(R.id.image_item)
        //adapter.context permet d'acceder à la mainactivity
        Glide.with(adapter.context).load(Uri.parse(currentPlant.imageUrl)).into(plantImage)

        // Actualiser le nom de la plante
        findViewById<TextView>(R.id.popup_plant_name).text = currentPlant.name

        //Actualiser la description
        findViewById<TextView>(R.id.popup_plant_name_subtitle).text = currentPlant.description

        //Actualiser de la croissance
        findViewById<TextView>(R.id.popup_plant_grow_subtitle).text = currentPlant.grow

        //Actualiser la consommation d'eau
        findViewById<TextView>(R.id.popup_plant_water_subtitle).text = currentPlant.water
    }



}