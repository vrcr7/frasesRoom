package com.example.frasesroom.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TABLE_FRASES")
data class Frases (
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int?,
    @ColumnInfo(name = "frase")
    val frase: String,

)