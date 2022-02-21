package com.example.mi_order.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_order.FoodRepository.Singleton.foodList
import com.example.mi_order.MainActivity
import com.example.mi_order.R
import com.example.mi_order.adapters.FoodAdapter
import com.example.mi_order.adapters.FoodItemDecoration

class HomeFragments(
    private val context: MainActivity
): Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
        ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        //recuperer le recyclerview
        //val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        //horizontalRecyclerView.adapter =
          //  FoodAdapter(context,foodList,R.layout.item_horizontal_food)

        //recuperer le second recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter =
            FoodAdapter(context, foodList, R.layout.item_vertical_food)
        verticalRecyclerView.addItemDecoration(FoodItemDecoration())
        return view
    }
}