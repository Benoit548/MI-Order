package com.example.mi_order.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_order.repository.FoodRepository
import com.example.mi_order.MainActivity
import com.example.mi_order.R
import com.example.mi_order.models.OrderModel
import com.example.mi_order.popups.FoodPopup
import com.example.mi_order.popups.OrderPopup
import com.example.mi_order.repository.OrderRepository

class OrderAdapter (
    val context: MainActivity,
    private val orderList: List<OrderModel>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<OrderAdapter.ViewHolder>(){

        // boite pour ranger tous les composants à contrôler
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
            val orderName = view.findViewById<TextView>(R.id.command_name)
            val orderFood: TextView?= view.findViewById(R.id.command_food_name)
            val orderPrecision: TextView?= view.findViewById(R.id.command_precision_name)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater
                .from(parent.context)
                .inflate(layoutId, parent, false )
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            //Recuperer les informations
            val currentOrder = orderList[position]

            //Recuperer le repository
            val repo = OrderRepository()


            // mettre à jour le nom
            holder.orderName?.text = currentOrder.userName

            //Mettre à jour
            holder.orderFood?.text = currentOrder.food

            //Mettre à jour
            holder.orderPrecision?.text = currentOrder.precision

            //Interaction lors d'un clic sur food, faire afficher le popup
            holder.itemView.setOnClickListener{
                //Afficher la popup
                OrderPopup(this, currentOrder).show()
            }




        }
        override fun getItemCount(): Int = orderList.size

}
