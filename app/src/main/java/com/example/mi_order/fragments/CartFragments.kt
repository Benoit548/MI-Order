package com.example.mi_order.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_order.*
import com.example.mi_order.adapters.FoodAdapter
import com.example.mi_order.adapters.FoodItemDecoration

class CartFragment(
    private val context: MainActivity
) : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_cart, container, false)

        //recuperer le second recyclerview
        val cartRecyclerView = view.findViewById<RecyclerView>(R.id.cart_recyclerView)
        cartRecyclerView.adapter =
            FoodAdapter(context, FoodPopup.Singleton.cartFoodList, R.layout.item_cartfood)
        cartRecyclerView.addItemDecoration(FoodItemDecoration())
        //Recuper le bouton close

        val deleteCart = view.findViewById<ImageView>(R.id.delete_cart)
        //deleteCart.setOnClickListener(FoodPopup.Singleton.cartFoodList.remove())


        return view
    }
}




