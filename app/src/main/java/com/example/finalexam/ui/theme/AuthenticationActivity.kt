package com.example.finalexam.ui.theme

    import android.content.Intent
    import androidx.appcompat.app.AppCompatActivity
    import android.os.Bundle
    import android.widget.Toast
    import com.example.finalexam.MainActivity
    import com.example.finalexam.R
    import com.google.firebase.auth.FirebaseAuth

    class AuthenticationActivity : AppCompatActivity() {

        private lateinit var auth: FirebaseAuth

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_authentication)

            // Initialize Firebase Auth
            auth = FirebaseAuth.getInstance()

            // Example usage:
            // signIn("example@email.com", "password123")
            // signUp("example@email.com", "password123")
            // signOut()
        }

        private fun signIn(email: String, password: String) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        redirectToMainActivity()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }

        private fun signUp(email: String, password: String) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up success, update UI with the signed-up user's information
                        redirectToMainActivity()
                    } else {
                        // If sign up fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }

        private fun signOut() {
            auth.signOut()
            // Redirect to login activity or perform any other necessary actions
        }

        private fun redirectToMainActivity() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

