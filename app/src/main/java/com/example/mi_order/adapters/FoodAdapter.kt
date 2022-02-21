package com.example.mi_order.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mi_order.*



class FoodAdapter(
    val context: MainActivity,
    private val foodList: List<FoodModel>,
    private val layoutId: Int
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>(){

    // boite pour ranger tous les composants à contrôler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val foodImage = view.findViewById<ImageView>(R.id.image_item)
        val foodName:TextView?= view.findViewById(R.id.name_item)
        val foodUnit:TextView?= view.findViewById(R.id.unit_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Recuperer les informations de la plante
        val currentFood = foodList[position]

        //Recuperer le repository
        val repo = FoodRepository()

        //utiliser glide(une dépendance) pour récupérer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentFood.imageUrl)).into(holder.foodImage)

        // mettre à jour le nom de la plante
        holder.foodName?.text = currentFood.name

        //Mettre à jour les units
        holder.foodUnit?.text = currentFood.unit.toString()

        //Interaction lors d'un clic sur food, faire afficher le popup
        holder.itemView.setOnClickListener{
            //Afficher la popup
            FoodPopup(this, currentFood).show()
        }

    }

    override fun getItemCount(): Int = foodList.size
}