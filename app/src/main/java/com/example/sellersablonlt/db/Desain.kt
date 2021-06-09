package com.example.sellersablonlt.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "desain")
data class Desain(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "nama")
    val name: String,

    @ColumnInfo(name = "deskripsi")
    val desc: String,

    @ColumnInfo(name = "harga")
    val harga: String,

    @ColumnInfo(name = "image")
    val image: String
)