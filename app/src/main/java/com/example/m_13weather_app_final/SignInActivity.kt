package com.example.m_13weather_app_final

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.m_13weather_app_final.databinding.ActivitySignInBinding
import com.example.m_13weather_app_final.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnLogin = findViewById<Button>(R.id.button)
        val emailEt = findViewById<EditText>(R.id.emailEt)
        val password = findViewById<EditText>(R.id.passET)

        val sharedPreferences = getSharedPreferences("MY_PRE", Context.MODE_PRIVATE)
        val getUsername = sharedPreferences.getString("EMAIL","")


        if (getUsername != "" ){
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }


        firebaseAuth = FirebaseAuth.getInstance()

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            val editor = sharedPreferences.edit()
            editor.putString("EMAIL", email)
            editor.putString("PASS", pass)
            editor.apply()


            val i = Intent(this,MainActivity::class.java)
            startActivity(i)

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            } else {
                Toast.makeText(this, "Empty Fields Are not Allowed", Toast.LENGTH_SHORT).show()

            }
        }
    }

//    override fun onStart() {
//        super.onStart()
//        if (firebaseAuth.currentUser != null) {
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//        }
//    }

}