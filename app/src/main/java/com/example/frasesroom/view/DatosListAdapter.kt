package com.example.frasesroom.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frasesRoom.R
import com.example.frasesroom.model.Frases

class DatosListAdapter(private var datos: List<Frases>) :
    RecyclerView.Adapter<DatosListAdapter.DatosViewHolder>() {

    // Crea una nueva vista para cada elemento del RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatosViewHolder {
        // Infla el layout del elemento de lista desde el archivo de diseño 'article_item.xml'
        val view = LayoutInflater.from(parent.context).inflate(R.layout.article_item, parent, false)
        return DatosViewHolder(view)
    }

    // Vincula los datos del elemento en la posición dada con la vista
    override fun onBindViewHolder(holder: DatosViewHolder, position: Int) {
        // Obtiene los datos actuales en la posición dada
        val currentDatos = datos[position]
        // Vincula los datos con la vista del titular de los datos
        holder.bind(currentDatos)
    }

    // Devuelve la cantidad total de elementos en la lista de datos
    override fun getItemCount(): Int = datos.size

    // Define la clase ViewHolder que representa cada elemento de la lista
        class DatosViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            // Referencias a los elementos de la vista
            private val fraseTextView: TextView = view.findViewById(R.id.fraseTxt)


        // Vincula los datos con los elementos de la vista
        fun bind(datos: Frases) {
            // Establece los valores de los campos de texto con los datos correspondientes
            fraseTextView.text = datos.frase

        }
    }
}