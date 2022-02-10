package com.example.mi_order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mi_order.fragments.AddPlantFragment
import com.example.mi_order.fragments.CollectionFragment
import com.example.mi_order.fragments.HomeFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        // Importer la bottomnavigationview
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.home_page -> {
                    loadFragment(HomeFragment(this),R.string.home_page_tittle)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this),R.string.collection_page_tittle)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.add_plat_page -> {
                    loadFragment(AddPlantFragment(this),R.string.add_plant_tittle)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

    }

    private fun loadFragment(fragment: Fragment, string: Int) {
        // charger notre PlantRepository
        val repo = PlantRepository()

        //Actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        // mettre à jour la liste de plantes
        repo.updateData{ // On effectue les actions une fois que le callback à été effectué dans le plantRepository
            //injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }


    }

}