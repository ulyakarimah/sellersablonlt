package com.example.sellersablonlt.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "toko")
data class Toko(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "nama")
    val name: String,

    @ColumnInfo(name = "alamat")
    val alamat: String,

    @ColumnInfo(name = "image")
    val image: String
)
