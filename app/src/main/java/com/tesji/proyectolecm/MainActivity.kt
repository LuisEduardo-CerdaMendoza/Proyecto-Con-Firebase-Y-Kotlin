package com.tesji.proyectolecm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class MainActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIniciarS : Button = findViewById(R.id.btnIniciarS)
        val txtUser : TextView = findViewById(R.id.txtUser)
        val txtContrasena : TextView = findViewById(R.id.txtContrasena)
        val btnCrearCuenta : TextView = findViewById(R.id.btnCrearCuenta)
        val btnOlvide : TextView = findViewById(R.id.btnOlvide)
        firebaseAuth = Firebase.auth

        btnIniciarS.setOnClickListener(){

            if(txtUser.text.toString().trim().isEmpty() && txtContrasena.text.toString().trim().isEmpty()){
                txtUser.setError("Ingrese su Email")
                txtContrasena.setError("Ingresa tu Contraseña")
                return@setOnClickListener
            }else if(txtContrasena.text.toString().trim().isEmpty()){
                txtContrasena.setError("Ingresa tu Contraseña")
                return@setOnClickListener
            }else{
                signIn(txtUser.text.toString(), txtContrasena.text.toString())
            }
        }

        btnCrearCuenta.setOnClickListener(){
            val i = Intent(this, CrearCuentaActivity::class.java)
            startActivity(i)
        }

        btnOlvide.setOnClickListener(){
            val i = Intent(this, olvideContra::class.java)
            startActivity(i)
        }

    }

    private fun signIn(email: String, password: String){

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){ task ->

            if (task.isSuccessful){
                val user = firebaseAuth.currentUser
                val verifica = user?.isEmailVerified
                if (verifica == true){
                    Toast.makeText(baseContext, "Inicio Exitoso", Toast.LENGTH_SHORT).show()
                    //aqui vamos a ir a la segunda pantalla
                    val i = Intent(this, pantalla_inicio::class.java)
                    startActivity(i)
                }else{
                    Toast.makeText(baseContext, "Verifica tu Correo en la bandeja de entrada o en el spam", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(baseContext, "Error de Email y/o Contraseña", Toast.LENGTH_SHORT).show()
            }

        }

    }

}