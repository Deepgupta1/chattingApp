package com.example.android.chatingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.activity_sign_up.BtnSignup
import kotlinx.android.synthetic.main.activity_sign_up.edit_email as edit_email1
import kotlinx.android.synthetic.main.activity_sign_up.edit_password as edit_password1

class SignUpActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef :DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mAuth=FirebaseAuth.getInstance()

        supportActionBar?.hide()

        BtnSignup.setOnClickListener{
            val email=edit_email.text.toString()
            val password=edit_password.text.toString()
            val name=edit_Name.text.toString()
            signUp(name,email,password)
        }
    }

    private fun signUp(name: String,email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)//doubt
                    startActivity(Intent(this@SignUpActivity,MainActivity::class.java))
                    finish()


                } else {
                    // If sign in fails, display a message to the user.

                    Log.d("error",task.toString())
                Toast.makeText(this@SignUpActivity,"Some error occured",Toast.LENGTH_SHORT).show()
                }
            }


    }

    private fun addUserToDatabase(name: String, email: String, uid: String) {
        mDbRef=FirebaseDatabase.getInstance().getReference()


        //it make a child node name user and then make user child name uid and setting values
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))//here User is model class

    }
}