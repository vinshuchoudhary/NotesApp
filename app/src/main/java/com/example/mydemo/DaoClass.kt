package com.example.mydemo

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface DaoClass {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note: EntityClass)

    @Delete
    suspend fun delete(note: EntityClass)

    @Query("select * from demoTable")
    fun getAllNotes(): LiveData<List<EntityClass>>

    @Query("update demoTable set title = :mtitle , description =:mdescription where id=:mid")
    suspend fun updateNote(mtitle :String , mdescription:String,mid: Int)

}