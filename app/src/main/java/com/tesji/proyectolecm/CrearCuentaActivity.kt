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

class CrearCuentaActivity : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_cuenta)

        val txtNombreNuevo : TextView = findViewById(R.id.txtNombre)
        val txtEmailNuevo : TextView = findViewById(R.id.txtEmail)
        val txtContra : TextView = findViewById(R.id.txtContraCorreo)
        val txtConfirmarContra : TextView = findViewById(R.id.txtConfirmarContra)
        val btnCrearNuevaCuenra : Button = findViewById(R.id.btnCrearNuevaCuenta)
        val volver : ImageView = findViewById(R.id.imageVolver)

        firebaseAuth = Firebase.auth

        btnCrearNuevaCuenra.setOnClickListener(){

            if(txtNombreNuevo.text.toString().trim().isEmpty() || txtEmailNuevo.text.toString().trim().isEmpty() || txtContra.text.toString().trim().isEmpty()
                || txtConfirmarContra.text.toString().trim().isEmpty()){

                txtNombreNuevo.setError("Ingrese Nombre")
                txtEmailNuevo.setError("Ingrese Email")
                txtContra.setError("Ingrese Contraseña")
                txtConfirmarContra.setError("Ingrese la Confirmacion de la contraseña")

                return@setOnClickListener
            }else{

                var Contra1 = txtContra.text.toString()
                var Contra2 = txtConfirmarContra.text.toString()

                if (Contra1.equals(Contra2)){
                    createAccount(txtEmailNuevo.text.toString(), txtContra.text.toString())
                }else{
                    Toast.makeText(baseContext, "Error: las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                    txtContra.requestFocus()
                }

            }

        }

        volver.setOnClickListener(){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

    }

    private fun createAccount(email: String, password: String){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if (task.isSuccessful){
                    sendEmailVerification()
                    Toast.makeText(baseContext, "Cuenta Creada Correctamente, se requiere verificacion", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(baseContext, "Algo Salio Mal, Error: " + task.exception, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun sendEmailVerification(){
        val user = firebaseAuth.currentUser!!
        user.sendEmailVerification().addOnCompleteListener(this){task ->
            if(task.isSuccessful){

            }else{

            }
        }
    }

}