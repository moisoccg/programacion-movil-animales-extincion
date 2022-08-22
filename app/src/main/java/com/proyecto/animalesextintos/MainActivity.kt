package com.proyecto.animalesextintos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.proyecto.animalesextintos.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Init

        FirebaseApp.initializeApp(this)
        auth = Firebase.auth

        //Assign methods to buttons
        binding.login.setOnClickListener {
            login()
        }

        binding.register.setOnClickListener {
            register()
        }

        binding.btnForgotPassword.setOnClickListener {
            startActivity(Intent(applicationContext, ResetPasswordActivity::class.java))
        }
    }


    private fun login(){
        val email = binding.editTextTextEmailAddress4.text.toString()
        val password = binding.editTextTextPassword4.text.toString()

        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this){
                task->
            if(task.isSuccessful){
                val user = auth.currentUser
                Log.d("User Login", "Success")
                if (user != null) {
                    update(user)
                }
            }else{
                Log.d("User Login", "Failed")
                Log.d("Error", "Error: " + task.getException().toString())
                Toast.makeText(baseContext,"Failed", Toast.LENGTH_LONG)
            }
        }
    }
    private fun register(){
        val email = binding.editTextTextEmailAddress4.text.toString()
        val password = binding.editTextTextPassword4.text.toString()

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){
                task->
            if(task.isSuccessful){
                val user = auth.currentUser
                Log.d("User Login", "Sucess")
                Toast.makeText(baseContext,"Success", Toast.LENGTH_LONG)
            }else{
                Log.d("User Login", "Failed")
                Toast.makeText(baseContext,"Failed", Toast.LENGTH_LONG)
            }
        }
    }

    private fun update(user: FirebaseUser){
        if(user != null){
            val intent = Intent(this, Principal::class.java)
            startActivity(intent)
        }
    }

    public override fun onStart(){
        super.onStart()
        val user = auth.currentUser
        if (user != null) {
            update(user)
        }
    }
}