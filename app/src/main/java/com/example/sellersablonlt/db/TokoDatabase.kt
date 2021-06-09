package com.example.sellersablonlt.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf((Toko::class),(Desain::class)), version = 1, exportSchema = false)
abstract class TokoDatabase: RoomDatabase() {
    abstract fun tokoDao(): TokoDao

    companion object{
        private var INSTANCE: TokoDatabase? = null

        fun getInstance(context: Context): TokoDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        TokoDatabase::class.java,
                        "toko.db"
                    ).build()
                }
                return instance
            }
        }
    }
}