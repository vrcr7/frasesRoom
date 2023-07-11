package com.example.frasesroom.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.frasesRoom.R
import com.example.frasesRoom.databinding.ActivityMainBinding
import com.example.frasesroom.model.Frases
import com.example.frasesroom.model.FrasesRoomDatabase
import com.example.frasesroom.model.repository.FrasesRepository
import com.example.frasesroom.view.viewModel.FrasesViewModel
import com.example.frasesroom.view.viewModel.FrasesViewModelFactory

class MainActivity : AppCompatActivity(),BlankFragment.ListaButtonClickListener  {
    private lateinit var binding: ActivityMainBinding
    private lateinit var frasesViewModel: FrasesViewModel
    internal lateinit var data: List<Frases>

       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)
           binding = ActivityMainBinding.inflate(layoutInflater)
           setContentView(binding.root)

           //instanciar la bd
           val database = FrasesRoomDatabase.getDatabase(applicationContext)
           val frasesDao = database.frasesDao()
           val frasesRepository = FrasesRepository(frasesDao)
           val frasesViewModelFactory = FrasesViewModelFactory(frasesRepository)
           frasesViewModel = ViewModelProvider(this, frasesViewModelFactory).get(FrasesViewModel::class.java)

           frasesViewModel.allDatos.observe(this, Observer { datosList ->
                // Aquí cargamos la variable global data con lta lista de allDatos
                this.data = datosList
            })
            //cargar fragmento con supportFragmentManager
           supportFragmentManager.commit {
               setReorderingAllowed(true)
               add<BlankFragment>(R.id.fragmentContainer)
                 }
             }

    override fun onListaButtonClick() {
        try {
            // Verificar si la propiedad ids está inicializada
            if (!data.isEmpty()) {
                // val total = this.data.sumOf { it.precio }
                // Acceder a la propiedad ids
                val listaFragment = ListaFragment()
                val bundle = Bundle()
                //   bundle.putString("total", total.toString())
                listaFragment.arguments = bundle
                supportFragmentManager.commit {
                    replace(R.id.fragmentContainer,listaFragment)
                    addToBackStack(null)
                }
            } else {
                // La propiedad ids no ha sido inicializada
                // Realiza la lógica correspondiente en este caso
                Toast.makeText(this, "la lista está vacía", Toast.LENGTH_SHORT).show()
            }

        } catch (e: Exception) {
            // Manejar la excepción en caso de que ocurra algún error
            Toast.makeText(this, "Error al acceder a los datos", Toast.LENGTH_SHORT).show()
            e.printStackTrace()
        }
    }
    // Función para el botón insertar
    override fun insertar() {
        val fraseEditText = findViewById<EditText>(R.id.textFrase)
        val frase = fraseEditText.text.toString()
        val datos = Frases(null, frase)
        frasesViewModel.insert(datos)
        Toast.makeText(this, "agregado correctamente", Toast.LENGTH_SHORT).show()
    }
    fun eliminarUno() {
        if (data.isNotEmpty()) {
            val primerValor = data.first()
            var indicePrimerValor = data.indexOf(primerValor)
            var valorAEliminar = primerValor.id //pedimos el id
            // Llamada a la función de eliminación en el ViewModel
            frasesViewModel.eliminarUno(valorAEliminar!!)
            // Actualizar la lista de datos
            data = data.filterNot { it.id == valorAEliminar }
            // Actualizar la vista con el fragmento ListaFragment
            val listaFragment = ListaFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, listaFragment)
                .commit()
        }
    }

    fun eliminar()
    {
        frasesViewModel.deleteAll()
        data = emptyList()
        val listaFragment = ListaFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, listaFragment)
            .commit()
    }




}