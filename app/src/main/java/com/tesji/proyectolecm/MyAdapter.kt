package com.tesji.proyectolecm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tesji.proyectolecm.model.User

class MyAdapter (private val userList: ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_items, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user: User = userList[position]
        holder.idJuego.text = user.ID
        holder.nombreJuego.text = user.Nombre_del_juego
        holder.tipoJuego.text = user.Tipo_de_Juego
        holder.nombrePersonaje.text = user.Nombre_del_personaje
        holder.jugabilidad.text = user.Jugabilidad
    }

    public class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idJuego: TextView = itemView.findViewById(R.id.txtIdJuego)
        val nombreJuego: TextView = itemView.findViewById(R.id.txtNomJuego)
        val tipoJuego: TextView = itemView.findViewById(R.id.txtTipJuego)
        val nombrePersonaje: TextView = itemView.findViewById(R.id.txtNomPersonaje)
        val jugabilidad: TextView = itemView.findViewById(R.id.txtJugabilidad)
    }

}