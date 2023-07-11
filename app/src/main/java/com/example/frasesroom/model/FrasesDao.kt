package com.example.frasesroom.model
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface FrasesDao {

    @Query("SELECT * FROM TABLE_FRASES ORDER BY id ASC")
    fun getAllDatos(): LiveData<List<Frases>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(datos: Frases)

    @Query("DELETE FROM TABLE_FRASES")
    suspend fun deleteAll()

    @Query("DELETE FROM TABLE_FRASES where id=:id")
    suspend fun deleteUno(id:Int)

    @Query("UPDATE TABLE_FRASES SET frase =:nuevaFrase WHERE id=:idFrase")
    suspend fun updateFrase(idFrase:Int,nuevaFrase:String)
}