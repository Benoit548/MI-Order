package com.example.mi_order

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mi_order.adapters.FoodAdapter

class FoodPopup (
    private val adapter: FoodAdapter,
    private val currentFood: FoodModel,
    ) : Dialog(adapter.context) {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.popup_food_details)
            setupComponents()
            setupCloseButton()

        }

        private fun setupCloseButton() {
            findViewById<ImageView>(R.id.close_button).setOnClickListener{
                //fermer la fenetre
                dismiss()
            }
        }

        private fun setupComponents() {
            // actualiser l'image
            val foodImage = findViewById<ImageView>(R.id.image_item)
            //adapter.context permet d'acceder à la mainactivity
            Glide.with(adapter.context).load(Uri.parse(currentFood.imageUrl)).into(foodImage)

            // Actualiser le nom
            findViewById<TextView>(R.id.popup_food_name).text = currentFood.name

            //Actualiser la description
            findViewById<TextView>(R.id.popup_food_name_subtitle).text = currentFood.description

        }



    }