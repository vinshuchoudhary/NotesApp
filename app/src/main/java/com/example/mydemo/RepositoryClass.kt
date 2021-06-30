package com.example.mydemo

import androidx.lifecycle.LiveData

class RepositoryClass(private val dao: DaoClass) {

    val allNotes : LiveData<List<EntityClass>> = dao.getAllNotes()

    suspend fun insert(note: EntityClass){
        dao.insert(note)
    }

    suspend fun delete(note: EntityClass){
        dao.delete(note)
    }

    suspend fun update(mtitle :String , mdescription:String,mid: Int){
        dao.updateNote(mtitle, mdescription, mid)
    }

}