package com.example

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth

class LandingPage : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landingpage)

        firebaseAuth = FirebaseAuth.getInstance()

        val fabButton: FloatingActionButton = findViewById(R.id.floatingActionButton)
        fabButton.setOnClickListener {
            val intent = Intent(this@LandingPage, HomeActivity::class.java)
            startActivity(intent)
        }

        val fabButton1: FloatingActionButton = findViewById(R.id.floatingActionButton1)
        fabButton1.setOnClickListener {
            val intent = Intent(this@LandingPage, MainPageActivity::class.java)
            startActivity(intent)
        }

        val logoutButton: Button = findViewById(R.id.logout_button)
        logoutButton.setOnClickListener {
            firebaseAuth.signOut() // Sign out the user
            val intent = Intent(this@LandingPage, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
