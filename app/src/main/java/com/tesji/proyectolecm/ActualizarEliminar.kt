package com.tesji.proyectolecm

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ActualizarEliminar : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var authStateListener: FirebaseAuth.AuthStateListener

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_eliminar)

        var db = Firebase.firestore
        firebaseAuth = Firebase.auth

        val btnRecuperar : Button = findViewById(R.id.btnRecuperar)
        val btnActualizar : Button = findViewById(R.id.btnActualizar)
        val btnEliminar : Button = findViewById(R.id.btnEliminar)
        val btnLimpiar : Button = findViewById(R.id.btnLimpiar)
        val btnvolver : ImageView = findViewById(R.id.imageVolverIn)
        val txtIdA : TextView = findViewById(R.id.txtIdA)
        val txtNombreJuegoA : TextView = findViewById(R.id.txtNombreJuegoA)
        val txtTipoDeJuegoA : TextView = findViewById(R.id.txtTipoDeJuegoA)
        val txtNombreDelPersonajeA : TextView = findViewById(R.id.txtPersonajeDeJuegoA)
        val txtJugabilidadA : TextView = findViewById(R.id.txtJugabilidadMasFacilA)

        btnvolver.setOnClickListener(){
            val i = Intent(this, pantalla_inicio::class.java)
            startActivity(i)
        }

        val Gameid = intent.getStringExtra("ID")
        txtIdA.setText(Gameid)

        btnRecuperar.setOnClickListener(){

            if(txtIdA.text.toString().trim().isEmpty()){

                txtIdA.setError("Ingresa Id")

                return@setOnClickListener

            }else{
                db = FirebaseFirestore.getInstance()

                db.collection("user").document(txtIdA.text.toString()).get().addOnSuccessListener { documentSnapshot ->
                    // Obtén el ID del documento
                    val documentId = documentSnapshot.id

                    // Utiliza el ID como necesites (puedes imprimirlo, almacenarlo, etc.)
                    Toast.makeText(this, "ID del documento: $documentId", Toast.LENGTH_SHORT).show()

                    // Resto del código para llenar los campos con los valores del documento
                    txtNombreJuegoA.setText(documentSnapshot.get("Nombre_del_juego") as String?)
                    txtTipoDeJuegoA.setText(documentSnapshot.get("Tipo_de_Juego") as String?)
                    txtNombreDelPersonajeA.setText(documentSnapshot.get("Nombre_del_personaje") as String?)
                    txtJugabilidadA.setText(documentSnapshot.get("Jugabilidad") as String?)
                }
            }

        }

        btnActualizar.setOnClickListener(){
            if(txtIdA.text.toString().trim().isEmpty() || txtNombreJuegoA.text.toString().trim().isEmpty() || txtTipoDeJuegoA.text.toString().trim().isEmpty()
                || txtNombreDelPersonajeA.text.toString().trim().isEmpty() || txtJugabilidadA.text.toString().trim().isEmpty()){

                txtIdA.setError("Ingresa Id")
                txtNombreJuegoA.setError("Ingresa el Nombre del Juego")
                txtTipoDeJuegoA.setError("Ingresa el Tipo de Juego")
                txtNombreDelPersonajeA.setError("Ingresa el Nombre del Personaje")
                txtJugabilidadA.setError("Ingresa la Jugabilidad")

                return@setOnClickListener

            }else {
                db.collection("user").document(txtIdA.text.toString()).set(
                    hashMapOf(
                        "Nombre_del_juego" to txtNombreJuegoA.text.toString(),
                        "Tipo_de_Juego" to txtTipoDeJuegoA.text.toString(),
                        "Nombre_del_personaje" to txtNombreDelPersonajeA.text.toString(),
                        "Jugabilidad" to txtJugabilidadA.text.toString()
                    )
                )
            }
        }

        btnEliminar.setOnClickListener(){
                db = FirebaseFirestore.getInstance()
                db.collection("user").document(txtIdA.text.toString()).delete()

        }

        btnLimpiar.setOnClickListener(){
            txtIdA.setText("")
            txtNombreJuegoA.setText("")
            txtTipoDeJuegoA.setText("")
            txtNombreDelPersonajeA.setText("")
            txtJugabilidadA.setText("")
        }

    }
}