package com.example.mi_order

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mi_order.fragments.CartFragment

import com.example.mi_order.fragments.HomeFragments;
import com.example.mi_order.fragments.MenuFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragments(this),R.string.home_page_title)

        // Importer la bottomnavigationview
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.home_page -> {
                    loadFragment(HomeFragments(this),R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.menu_page -> {
                    loadFragment(MenuFragment(this),R.string.menu_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.cart_page -> {
                    loadFragment(CartFragment(this),R.string.cart_title)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }


    }

    private fun loadFragment(fragment: Fragment, string: Int) {
        // charger notre PlantRepository
        val repo = FoodRepository()

        //Actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        // mettre à jour la liste de food
        repo.updateData{ // On effectue les actions une fois que le callback à été effectué dans le foodRepository
            //injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }


    }
}
