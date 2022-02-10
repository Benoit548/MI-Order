package com.example.mi_order.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_order.PlantRepository.Singleton.plantList
import com.example.mi_order.adapter.PlantAdapter
import com.aksel.naturecollection.adapter.PlantItemDecoration
import com.example.mi_order.HomePage
import com.example.mi_order.R

class HomeFragment(
    private val context: HomePage
) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home,container,false)

        //recuperer le recyclerview
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = PlantAdapter(context,plantList.filter { !it.liked },R.layout.item_horizontal_plant)

        //recuperer le seconde recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = PlantAdapter(context,plantList, R.layout.item_vertical_plant)
        verticalRecyclerView.addItemDecoration(PlantItemDecoration())
        return view
    }
}