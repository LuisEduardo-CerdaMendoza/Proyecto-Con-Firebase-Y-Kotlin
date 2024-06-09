package com.tesji.proyectolecm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class pantalla_inicio : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    private var db = Firebase.firestore


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_inicio)

        val btnCerrar : Button = findViewById(R.id.btnCerrarSesion)
        val btnGuardar : Button = findViewById(R.id.btnGuardar)
        val btnVer : Button = findViewById(R.id.btnVerDatos)
        val btnActEl : Button = findViewById(R.id.btnActEl)
        val txtId : TextView = findViewById(R.id.txtId)
        val txtNombreJuego : TextView = findViewById(R.id.txtNombreJuego)
        val txtTipoDeJurgo : TextView = findViewById(R.id.txtTipoDeJuego)
        val txtNombreDelPersonaje : TextView = findViewById(R.id.txtPersonajeDeJuego)
        val txtJugabilidad : TextView = findViewById(R.id.txtJugabilidadMasFacil)

        btnCerrar.setOnClickListener(){
            signOut()
        }

        btnVer.setOnClickListener(){
            val i = Intent(this, FecthData::class.java)
            startActivity(i)
        }

        btnActEl.setOnClickListener(){
            val i = Intent(this, ActualizarEliminar::class.java)
            startActivity(i)
        }

        btnGuardar.setOnClickListener {

            if(txtId.text.toString().trim().isEmpty() || txtNombreJuego.text.toString().trim().isEmpty() || txtTipoDeJurgo.text.toString().trim().isEmpty()
                || txtNombreDelPersonaje.text.toString().trim().isEmpty() || txtJugabilidad.text.toString().trim().isEmpty()){

                txtId.setError("Ingresa Id")
                txtNombreJuego.setError("Ingresa el Nombre del Juego")
                txtTipoDeJurgo.setError("Ingresa el Tipo de Juego")
                txtNombreDelPersonaje.setError("Ingresa el Nombre del Personaje")
                txtJugabilidad.setError("Ingresa la Jugabilidad")

                return@setOnClickListener

            }else{

                db.collection("user").document(txtId.text.toString()).set(
                    hashMapOf(
                        "ID" to txtId.text.toString(),
                        "Nombre_del_juego" to txtNombreJuego.text.toString(),
                        "Tipo_de_Juego" to txtTipoDeJurgo.text.toString(),
                        "Nombre_del_personaje" to txtNombreDelPersonaje.text.toString(),
                        "Jugabilidad" to txtJugabilidad.text.toString(),
                    )
                )
                    .addOnSuccessListener {
                        Toast.makeText(this,"Guardado Con Exito",Toast.LENGTH_SHORT).show()
                        txtId.setText("")
                        txtNombreJuego.setText("")
                        txtTipoDeJurgo.setText("")
                        txtNombreDelPersonaje.setText("")
                        txtJugabilidad.setText("")
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Hubo un Error", Toast.LENGTH_SHORT).show()
                    }

            }
        }

    }

    @SuppressLint("MissingSuperCall")
    override fun onBackPressed() {
        return
    }

    private fun signOut() {
        firebaseAuth.signOut()
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }

}