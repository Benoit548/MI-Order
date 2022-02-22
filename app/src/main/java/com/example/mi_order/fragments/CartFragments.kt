package com.example.mi_order.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_order.*
import com.example.mi_order.adapters.FoodAdapter
import com.example.mi_order.adapters.FoodItemDecoration
import java.util.*

class CartFragment(
    private val context: MainActivity
) : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_cart, container, false)

        //Recuperer le bouton d'ajout
        val addButton = view.findViewById<Button>(R.id.confirm_button)
        addButton.setOnClickListener { sendOrder(view)}
        return view
    }

    private fun sendOrder(view: View) {
         val userName = view.findViewById<EditText>(R.id.name_input).text.toString()
         val userFood = view.findViewById<Spinner>(R.id.food_spinner).selectedItem.toString()

        //Créer un nouvel object order
        val order = OrderModel(
            UUID.randomUUID().toString(), //Permet de génener un identifiant unique
            userName,
            userFood
        )

        //Envoyer en BDD
       FoodRepository().insertOrder(order)
    }


}




