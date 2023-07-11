package com.example.frasesroom.view.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.frasesroom.model.Frases
import com.example.frasesroom.model.repository.FrasesRepository
import kotlinx.coroutines.launch


class FrasesViewModel (private val repository: FrasesRepository) : ViewModel()  {

    // devuelve todos los datos de la bd
    val allDatos: LiveData<List<Frases>> = repository.allDatos

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insert(frases: Frases) = viewModelScope.launch {
        repository.insert(frases)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    fun eliminarUno(id:Int) = viewModelScope.launch {
        repository.deleteUno(id)
    }

}

class FrasesViewModelFactory(private val repository: FrasesRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FrasesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FrasesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}