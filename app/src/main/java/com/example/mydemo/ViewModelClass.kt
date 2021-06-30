package com.example.mydemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelClass(application: Application) : AndroidViewModel(application) {

    val allNotes: LiveData<List<EntityClass>>
    private val repository: RepositoryClass

    init {

        val dao = DatabaseClass.getDatabase(application).getDao()
        repository = RepositoryClass(dao)

        allNotes = repository.allNotes

    }

    fun deleteNote(note: EntityClass) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertNote(note: EntityClass) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }

    fun updateNote(mtitle :String , mdescription:String,mid: Int) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(mtitle, mdescription, mid)
    }



}