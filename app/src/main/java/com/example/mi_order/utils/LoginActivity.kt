package com.example.mi_order.utils

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.widget.*
import com.example.mi_order.MainActivity
import com.example.mi_order.R

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page)

        val normalButton = findViewById<Button>(R.id.button_normal_co)
        val usernameInput = findViewById<EditText>(R.id.username_input)
        val passwordInput = findViewById<EditText>(R.id.password_input)

        fun getIntent(userName : String): Intent {
            val myIntent = Intent(this, MainActivity::class.java)
            val extras = Bundle()
            val userName = usernameInput.getText().toString()
            extras.putString(Constants.Login.EXTRA_LOGIN, userName)
            myIntent.putExtras(extras)
            return myIntent
        }


        normalButton.setOnClickListener {
            startActivity(getIntent(usernameInput.getText().toString()))
            finish()
        }


    }

}