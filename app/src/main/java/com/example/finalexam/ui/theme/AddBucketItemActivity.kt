package com.example.finalexam.ui.theme

    import android.os.Bundle
    import android.widget.Toast
    import androidx.appcompat.app.AppCompatActivity
    import com.google.firebase.firestore.FirebaseFirestore
    import kotlinx.android.synthetic.main.activity_add_bucket_item.*

    class AddBucketItemActivity : AppCompatActivity() {

        private val firestore = FirebaseFirestore.getInstance()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_add_bucket_item)

            // Click listener for adding a new bucket list item
            buttonAddDestination.setOnClickListener {
                // Get input values
                val destinationName = editTextDestinationName.text.toString()
                val description = editTextDescription.text.toString()
                val ranking = spinnerRanking.selectedItem.toString()

                // Check if all fields are populated
                if (destinationName.isEmpty() || description.isEmpty() || ranking.isEmpty()) {
                    Toast.makeText(this, "All fields must be populated", Toast.LENGTH_SHORT).show()
                } else {
                    // Save the information to Firestore
                    saveToFirestore(destinationName, description, ranking)
                }
            }
        }

        private fun saveToFirestore(destinationName: String, description: String, ranking: String) {
            // Add a new document with a generated ID
            firestore.collection("bucket_list_items")
                .add(mapOf(
                    "destination_name" to destinationName,
                    "description" to description,
                    "ranking" to ranking
                ))
                .addOnSuccessListener {
                    Toast.makeText(this, "Bucket list item added successfully", Toast.LENGTH_SHORT).show()
                    // Navigate back to main activity
                    finish()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error adding document: $e", Toast.LENGTH_SHORT).show()
                }
        }
    }
