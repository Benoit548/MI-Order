package com.example.mi_order

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mi_order.fragments.HomeFragments;

public class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Charger notre repository
        val repo = FoodRepository()

        //Mettre à jour la liste de food
        repo.updateData{

        //injecter le fragment dans notre boite(fragment_container)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, HomeFragments(this ))
        transaction.addToBackStack(null)
        transaction.commit()
        }


    }



}
