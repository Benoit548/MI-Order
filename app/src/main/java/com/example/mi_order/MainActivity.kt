package com.example.mi_order

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mi_order.fragments.CartFragment

import com.example.mi_order.fragments.HomeFragments;
import com.example.mi_order.fragments.MenuFragment
import com.example.mi_order.fragments.OrderFragments
import com.example.mi_order.repository.FoodRepository
import com.example.mi_order.repository.OrderRepository
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragments(this),R.string.home_page_title)
        val message = intent.getStringExtra("EXTRA_LOGIN")



        //Tester si Admin ou non
        val adminNavigationView = findViewById<BottomNavigationView>(R.id.navigation_view_admin)
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        if(message == "admin"){
            adminNavigationView.setVisibility(View.VISIBLE)
            navigationView.setVisibility(View.GONE)
        }
        else{
            adminNavigationView.setVisibility(View.GONE)
            navigationView.setVisibility(View.VISIBLE)
        }





        // Importer la bottomnavigationview

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

        adminNavigationView.setOnNavigationItemSelectedListener{
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
                R.id.command_admin -> {
                    loadFragment(OrderFragments(this),R.string.admin_section)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }

    }

    private fun loadFragment(fragment: Fragment, string: Int) {
        // charger notre FoodRepository
        val repo = FoodRepository()
        val orderRepo = OrderRepository()

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

        orderRepo.updateData{ // On effectue les actions une fois que le callback à été effectué dans le foodRepository
            //injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment)
            transaction.addToBackStack(null)
            transaction.commit()

        }

    }

}

