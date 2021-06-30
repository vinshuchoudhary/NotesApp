package com.example.mydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mydemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), INotesRVAdapter {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: ViewModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newNoteButton.setOnClickListener{
            val intent = Intent(this,EntryActivity::class.java)
            intent.putExtra("flag",1)
            startActivity(intent)
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get( ViewModelClass::class.java)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RVAdapter(this,this)
        binding.recyclerView.adapter = adapter

        viewModel.allNotes.observe(this, Observer {list ->
            list?.let {
                adapter.updateList(it)
            }
        })

    }

    override fun onItemClicked(note: EntityClass) {
        viewModel.deleteNote(note)
        Toast.makeText(this, "the ${note.title} note is deleted", Toast.LENGTH_SHORT).show()
    }


}