package com.example

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*

class ReceiptActivity : AppCompatActivity() {

    // Firebase database reference
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.receipt)

        // Initialize Firebase database reference
        databaseReference = FirebaseDatabase.getInstance().reference

        // Retrieve order summary from intent extra
        val orderSummary = intent.getStringExtra("orderSummary")

        // Display order summary in the TextView
        val orderSummaryTextView: TextView = findViewById(R.id.orderSummaryLabel)
        orderSummaryTextView.text = orderSummary

        // Retrieve user information from intent extras
        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val address = intent.getStringExtra("address")

        // Display user information in EditText fields
        val nameEditText: TextView = findViewById(R.id.nameEditText)
        val phoneEditText: TextView = findViewById(R.id.phoneEditText)
        val addressEditText: TextView = findViewById(R.id.addressEditText)

        nameEditText.text = name
        phoneEditText.text = phone
        addressEditText.text = address

        // Retrieve order items from intent extras
        val orderItems = intent.getStringArrayListExtra("orderItems")

        // Display order items in the LinearLayout
        val orderItemsLayout: LinearLayout = findViewById(R.id.orderItemsLayout)
        if (orderItems != null) {
            for (item in orderItems) {
                val textView = TextView(this)
                textView.text = item
                orderItemsLayout.addView(textView)
            }
        }

        // Access the "Submit Order" button
        val submitOrderButton: Button = findViewById(R.id.submitOrderButton)

        // Set click listener for the "Submit Order" button
        submitOrderButton.setOnClickListener {
            // Get the text from the EditText fields
            val nameText = nameEditText.text.toString().trim()
            val phoneText = phoneEditText.text.toString().trim()
            val addressText = addressEditText.text.toString().trim()

            // Check if any required field is empty
            if (nameText.isEmpty() || phoneText.isEmpty() || addressText.isEmpty() || orderSummary.isNullOrEmpty()) {
                showToast("Please fill in all fields before submitting the order.")
            } else {
                // Save order details to Firebase database
                saveOrderToDatabase(nameText, phoneText, addressText, orderSummary, orderItems)
                // Show a toast message that the order is on the way
                showToast("Your order is on the way!")
            }
        }
    }

    // Function to display a toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    // Function to save order details to Firebase database
    private fun saveOrderToDatabase(
        name: String,
        phone: String,
        address: String,
        orderSummary: String?,
        orderItems: ArrayList<String>?
    ) {
        // Create a new order object
        val order = Order(name, phone, address, orderSummary)

        // Generate a unique key for the order
        val orderId = databaseReference.child("orders").push().key

        // Save the order to the database under "orders" node with the generated key
        if (orderId != null) {
            databaseReference.child("orders").child(orderId).setValue(order)
        }

        // Save order items to the database under the order's key
        orderItems?.forEachIndexed { index, item ->
            databaseReference.child("orders").child(orderId!!).child("items").child(index.toString()).setValue(item)
        }
    }
}