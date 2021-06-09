package com.example.sellersablonlt.db

import androidx.room.*

@Dao
interface TokoDao {

    @Insert
    fun insertToko(toko: Toko)

    @Delete
    fun deleteToko(toko: Toko)

    @Query("select * from toko")
    fun getAllToko(): List<Toko>

    @Query("select * from toko where id = :id")
    fun getTokoById(id: Int): Toko

    @Insert
    fun insertDesain(desain : Desain)

    @Delete
    fun deleteDesain(desain: Desain)

    @Query("select * from desain")
    fun getAllDesain(): List<Desain>

    @Query("select * from desain where id = :id")
    fun getDesainById(id: Int): Desain
}

