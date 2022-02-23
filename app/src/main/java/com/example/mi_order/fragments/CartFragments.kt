package com.example.mi_order.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.fragment.app.Fragment
import com.example.mi_order.*
import com.example.mi_order.models.OrderModel
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

        //Déclaration des différents spinner pour alléger la fonction onItemSelected
        val spinnerFood = view.findViewById<Spinner>(R.id.food_spinner)
        val spinnerHotDog =  view.findViewById<Spinner>(R.id.hotdog_spinner)
        val spinnerRamen = view.findViewById<Spinner>(R.id.ramen_spinner)
        val spinnerCroque = view.findViewById<Spinner>(R.id.croque_spinner)
        val spinnerPanini = view.findViewById<Spinner>(R.id.panini_spinner)
        val spinnerSandwich = view.findViewById<Spinner>(R.id.sandwich_spinner)
        //Afficher les spinners

        spinnerFood.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (position == 0) spinnerHotDog.setVisibility(View.VISIBLE) else spinnerHotDog.setVisibility(
                    View.GONE
                )
                if (position == 1) spinnerRamen.setVisibility(View.VISIBLE) else spinnerRamen.setVisibility(
                    View.GONE
                )
                if (position == 2) spinnerCroque.setVisibility(View.VISIBLE) else spinnerCroque.setVisibility(
                    View.GONE
                )
                if (position == 3) spinnerPanini.setVisibility(View.VISIBLE) else spinnerPanini.setVisibility(
                    View.GONE
                )
                if (position == 4) spinnerSandwich.setVisibility(View.VISIBLE) else spinnerSandwich.setVisibility(
                    View.GONE
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

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




