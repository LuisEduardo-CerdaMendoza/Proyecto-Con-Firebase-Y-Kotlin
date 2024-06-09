package com.tesji.proyectolecm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class olvideContra : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_olvide_contra)

        val txtCorreo : TextView = findViewById(R.id.txtCorreoCambio)
        val btnCambiarContra : Button = findViewById(R.id.btnCambiar)
        val volver : ImageView = findViewById(R.id.imageVolver)

        firebaseAuth = Firebase.auth

        btnCambiarContra.setOnClickListener(){
            if(txtCorreo.text.toString().trim().isEmpty()){
                txtCorreo.setError("Ingresa tu Correo")
                return@setOnClickListener
            }else{
                sendPasswordReset(txtCorreo.text.toString())
            }

        }

        volver.setOnClickListener(){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    }

    private fun sendPasswordReset (email: String){
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(){task ->
            if (task.isSuccessful){
                Toast.makeText(baseContext, "Correo de Cambio de contraseña enviado", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(baseContext,"Error, no se a podido completar el proceso", Toast.LENGTH_SHORT).show()

            }
        }
    }

}