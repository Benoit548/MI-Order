package com.example.mi_order.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.mi_order.MainActivity
import com.example.mi_order.R
import com.example.mi_order.models.MenuModel

class MenuAdapter(
    private val context: MainActivity,
    private val menuList: List<MenuModel>,
    private val layoutId:Int) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    //boite pour ranger tous les composants à controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val menuImage = view.findViewById<ImageView>(R.id.image_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //recuperer les informations du menu
        val currentMenu = menuList[position]
        (holder.menuImage).setImageResource(currentMenu.image)


    }


    override fun getItemCount(): Int = menuList.size
}
