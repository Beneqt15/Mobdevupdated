package com.example

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class HomeActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.personalsystem)

        // Initialize Firebase Database
        database = FirebaseDatabase.getInstance().reference

        val studentEditText: EditText = findViewById(R.id.Student)
        val addressEditText: EditText = findViewById(R.id.Address)
        val nameEditText: EditText = findViewById(R.id.Name)
        val ageEditText: EditText = findViewById(R.id.Age)
        val emailEditText: EditText = findViewById(R.id.Email)
        val contactEditText: EditText = findViewById(R.id.Contact)
        val birthdateEditText: EditText = findViewById(R.id.Birthdate)
        val hobbiesEditText: EditText = findViewById(R.id.Hobbies)
        val registerButton: Button = findViewById(R.id.register_button1)
        val deleteButton: Button = findViewById(R.id.delete)

        registerButton.setOnClickListener {
            val student = studentEditText.text.toString().trim()
            val address = addressEditText.text.toString().trim()
            val name = nameEditText.text.toString().trim()
            val age = ageEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val contact = contactEditText.text.toString().trim()
            val birthdate = birthdateEditText.text.toString().trim()
            val hobbies = hobbiesEditText.text.toString().trim()

            if (student.isEmpty() || address.isEmpty() || name.isEmpty() || age.isEmpty() ||
                email.isEmpty() || contact.isEmpty() || birthdate.isEmpty() || hobbies.isEmpty()) {
                showToast("Please fill in all details")
                return@setOnClickListener
            }

            // Create a user object
            val user = User(name, address, age, email, contact, hobbies, birthdate)

            // Save user to Firebase
            database.child("users").child(student).setValue(user)
                .addOnSuccessListener {
                    showToast("Data saved successfully")
                }
                .addOnFailureListener {
                    showToast("Failed to save data")
                }
        }

        deleteButton.setOnClickListener {
            studentEditText.text.clear()
            addressEditText.text.clear()
            nameEditText.text.clear()
            ageEditText.text.clear()
            emailEditText.text.clear()
            contactEditText.text.clear()
            birthdateEditText.text.clear()
            hobbiesEditText.text.clear()
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
