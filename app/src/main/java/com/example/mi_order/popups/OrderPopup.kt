package com.example.mi_order.popups

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.mi_order.R
import com.example.mi_order.adapters.FoodAdapter
import com.example.mi_order.adapters.OrderAdapter
import com.example.mi_order.models.FoodModel
import com.example.mi_order.models.OrderModel
import com.example.mi_order.repository.OrderRepository

class OrderPopup (
    private val adapter: OrderAdapter,
    private val currentOrder: OrderModel,
) : Dialog(adapter.context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_order_details)
        setupDeleteButton()



    }
    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener{
            //Supprimer
            val repo = OrderRepository()
            repo.deleteOrder(currentOrder)
            dismiss()
        }
    }





    }
