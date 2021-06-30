package com.example.mydemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(EntityClass::class),version = 1,exportSchema = false)
abstract class DatabaseClass :RoomDatabase(){

    abstract fun getDao() : DaoClass

    companion object{
        @Volatile
        private var INSTANCE: DatabaseClass? = null

        fun getDatabase(context: Context): DatabaseClass{

            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext,
                DatabaseClass::class.java,
                "note_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }

}