package com.example.mi_order.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_order.MainActivity
import com.example.mi_order.R
import com.example.mi_order.adapters.MenuAdapter
import com.example.mi_order.models.MenuModel

class MenuFragment(
    private val context: MainActivity
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater?.inflate(R.layout.fragment_menu, container, false)

        //Créer une liste qui va stocker les menus

        val menuList = arrayListOf<MenuModel>()

        //Enregistrer la liste
        menuList.add(MenuModel(
            image = R.drawable.trending6
        ))
        menuList.add(MenuModel(
            image = R.drawable.trending7
        ))
        menuList.add(MenuModel(
            image = R.drawable.trending8
        ))
        menuList.add(MenuModel(
            image = R.drawable.trending9
        ))

        //recuperer le recyclerview
        val horizontalRecyclerView = view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = MenuAdapter(context,menuList, R.layout.item_horizontal_menu)

        return view
    }
}
