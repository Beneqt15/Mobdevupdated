package com.example

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.foodpage)

        // Accessing the views from XML for each item
        val quantityTextView1: TextView = findViewById(R.id.quantity_text)
        val decreaseButton1: Button = findViewById(R.id.decrease_button)
        val increaseButton1: Button = findViewById(R.id.increase_button)

        val quantityTextView2: TextView = findViewById(R.id.quantity_text1)
        val decreaseButton2: Button = findViewById(R.id.decrease_button1)
        val increaseButton2: Button = findViewById(R.id.increase_button1)

        val quantityTextView3: TextView = findViewById(R.id.quantity_text2)
        val decreaseButton3: Button = findViewById(R.id.decrease_button2)
        val increaseButton3: Button = findViewById(R.id.increase_button2)

        val quantityTextView4: TextView = findViewById(R.id.quantity_text3)
        val decreaseButton4: Button = findViewById(R.id.decrease_button3)
        val increaseButton4: Button = findViewById(R.id.increase_button3)

        // Setting click listeners for each item's increase and decrease buttons
        decreaseButton1.setOnClickListener {
            decreaseQuantity(quantityTextView1)
        }

        increaseButton1.setOnClickListener {
            increaseQuantity(quantityTextView1)
        }

        decreaseButton2.setOnClickListener {
            decreaseQuantity(quantityTextView2)
        }

        increaseButton2.setOnClickListener {
            increaseQuantity(quantityTextView2)
        }

        decreaseButton3.setOnClickListener {
            decreaseQuantity(quantityTextView3)
        }

        increaseButton3.setOnClickListener {
            increaseQuantity(quantityTextView3)
        }

        decreaseButton4.setOnClickListener {
            decreaseQuantity(quantityTextView4)
        }

        increaseButton4.setOnClickListener {
            increaseQuantity(quantityTextView4)
        }

        // Accessing the "Order" button from XML
        val orderButton: Button = findViewById(R.id.order)

        // Setting click listener for the "Order" button
        orderButton.setOnClickListener {
            // Get the quantities from TextViews
            val quantity1 = quantityTextView1.text.toString().toInt()
            val quantity2 = quantityTextView2.text.toString().toInt()
            val quantity3 = quantityTextView3.text.toString().toInt()
            val quantity4 = quantityTextView4.text.toString().toInt()

            // Calculate total price for each item
            val priceTapsilog = quantity1 * 100
            val priceAdobo = quantity2 * 150
            val priceSinigang = quantity3 * 150
            val priceKareKare = quantity4 * 150

            // Calculate total price for all items
            val totalPrice = priceTapsilog + priceAdobo + priceSinigang + priceKareKare

            // Create the order summary
            val orderSummary = "Order added to cart\n" +
                    "Tapsilog: $quantity1 x 100 = ₱$priceTapsilog\n" +
                    "Adobo: $quantity2 x 150 = ₱$priceAdobo\n" +
                    "Sinigang: $quantity3 x 150 = ₱$priceSinigang\n" +
                    "Kare Kare: $quantity4 x 150 = ₱$priceKareKare\n" +
                    "Total Price: ₱$totalPrice"

            // Displaying a toast message with the order summary
            showToast(orderSummary)

            // Start ReceiptActivity and pass order summary and total price as extras
            val intent = Intent(this, ReceiptActivity::class.java)
            intent.putExtra("orderSummary", orderSummary)
            intent.putExtra("totalPrice", totalPrice)
            startActivity(intent)
        }
    }

    // Function to decrease quantity
    private fun decreaseQuantity(quantityTextView: TextView) {
        var quantity = quantityTextView.text.toString().toInt()
        if (quantity > 0) {
            quantity--
            quantityTextView.text = quantity.toString()
        }
    }

    // Function to increase quantity
    private fun increaseQuantity(quantityTextView: TextView) {
        var quantity = quantityTextView.text.toString().toInt()
        quantity++
        quantityTextView.text = quantity.toString()
    }

    // Function to display a toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
