package com.proyecto.animalesextintos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.proyecto.animalesextintos.databinding.ActivityMainBinding
import com.proyecto.animalesextintos.databinding.ActivityResetPasswordBinding

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password)

        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.btnResetPassword.setOnClickListener {
            val email = binding.edtResetEmail.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter your email!", Toast.LENGTH_SHORT).show()
            } else {
                auth!!.sendPasswordResetEmail(email)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@ResetPasswordActivity, "Verifique su correo electrónico para actualizar la contraseña", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this@ResetPasswordActivity, "Hubo un problema al enviar el correo!", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }

        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}