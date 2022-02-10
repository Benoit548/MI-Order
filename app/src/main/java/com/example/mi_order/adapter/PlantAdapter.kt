package com.example.mi_order.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mi_order.*

class PlantAdapter(
    val context: HomePage,
    private val plantList: List<PlantModel>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<PlantAdapter.ViewHolder>(){

    // boite pour ranger tous les composants à contrôler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val plantImage= view.findViewById<ImageView>(R.id.image_item)
        val plantName:TextView?= view.findViewById(R.id.name_item)
        val plantDescription:TextView?= view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //Recuperer les informations de la plante
        val currentPlant = plantList[position]

        //Recuperer le repository
        val repo = PlantRepository()

        //utiliser glide(une dépendance) pour récupérer l'image à partir de son lien -> composant
        Glide.with(context).load(Uri.parse(currentPlant.imageUrl)).into(holder.plantImage)

        // mettre à jour le nom de la plante
        holder.plantName?.text = currentPlant.name

        //Mettre à jour la description
        holder.plantDescription?.text = currentPlant.description

        //verifier si la plante à été liké ou non
        if(currentPlant.liked) {
            holder.starIcon.setImageResource(R.drawable.ic_star)
        }
        else{
            holder.starIcon.setImageResource(R.drawable.ic_unstar)
        }
        // rajouter une interaction sur l'étoile pour liker ou unliker
        holder.starIcon.setOnClickListener {
            //Inverse si le bouton est like ou non
            currentPlant.liked = !currentPlant.liked
            // mettre à jour l'objet plante
            repo.updatePlant(currentPlant)

        }

        // Interaction lors du clic sur une plante
        holder.itemView.setOnClickListener{
            //Afficher la popup
            PlantPopup(this, currentPlant).show()
        }
    }

    override fun getItemCount(): Int = plantList.size
}