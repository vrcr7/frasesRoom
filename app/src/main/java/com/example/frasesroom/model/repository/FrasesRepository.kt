package com.example.frasesroom.model.repository
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.frasesroom.model.Frases
import com.example.frasesroom.model.FrasesDao

class FrasesRepository(private val frasesDao: FrasesDao) {

    val allDatos: LiveData<List<Frases>> = frasesDao.getAllDatos()

    @WorkerThread
    suspend fun insert(frase: Frases) {
        frasesDao.insert(frase)
    }
    @WorkerThread
    suspend fun deleteAll() {
        frasesDao.deleteAll()
    }
    @WorkerThread
    suspend fun deleteUno(id:Int) {
        frasesDao.deleteUno(id)
    }

    @WorkerThread
    suspend fun updateFrase(idFrase:Int,nuevaFrase:String) {
        frasesDao.updateFrase(idFrase,nuevaFrase)
    }
}