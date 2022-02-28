package com.example.mi_order.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_order.MainActivity
import com.example.mi_order.R
import com.example.mi_order.adapters.FoodItemDecoration
import com.example.mi_order.adapters.OrderAdapter
import com.example.mi_order.repository.OrderRepository

class OrderFragments (
private val context: MainActivity
): Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_admin, container, false)

        //recuperer le recyclerview
        val adminRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_admin_recycler_view)
        adminRecyclerView.adapter =
            OrderAdapter(context, OrderRepository.Singleton.orderList, R.layout.item_command_vertical)
        adminRecyclerView.addItemDecoration(FoodItemDecoration())
        return view


    }


}