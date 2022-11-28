package com.example.android.chatingapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.BtnSignup
import kotlinx.android.synthetic.main.activity_login.edit_email
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.edit_password as edit_password1

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth =FirebaseAuth.getInstance()
        supportActionBar?.hide()

        BtnSignup.setOnClickListener{
            startActivity(Intent(this@LoginActivity,SignUpActivity::class.java))
        }
        Btnlogin.setOnClickListener{
            val email=edit_email.text.toString()
            val password= edit_password.text.toString()
            login(email,password)
        }

    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    startActivity(Intent(this@LoginActivity,MainActivity::class.java))
                    finish()

                } else {
                 Toast.makeText(this@LoginActivity,"User does not exist",Toast.LENGTH_SHORT).show()
                }
            }
    }
}