package com.example.mydemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mydemo.databinding.ActivityEntryBinding

class EntryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEntryBinding
    private lateinit var viewModel: ViewModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEntryBinding.inflate(layoutInflater)
        setContentView(binding.root)


        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get( ViewModelClass::class.java)

        val intent = Intent()
        if(intent.getIntExtra("flag",0) == 1) {
            Log.d("vinshu","we are in the updating block")
//            val index = intent.getIntExtra("index", viewModel.allNotes.value!!.size)
//            if(index<viewModel.allNotes.value!!.size) {
//                binding.title.text = viewModel.allNotes.value!!.get(index).title as Editable
//                binding.description.text = viewModel.allNotes.value!!.get(index).description as Editable
//                binding.updateButton.visibility = View.VISIBLE
//                Toast.makeText(
//                    this,
//                    "the clicked item is : ${viewModel.allNotes.value!!.get(index).title}",
//                    Toast.LENGTH_SHORT
//                ).show()
//                binding.updateButton.setOnClickListener {
//                    val title = binding.title.text.toString()
//                    val desc = binding.description.text.toString()
//                    viewModel.updateNote(EntityClass(title,desc))
//                    finish()
//                }
//            }
        }


    }

    fun submitData(view: View) {

        val title = binding.title.text.toString()
        val desc = binding.description.text.toString()

        if(title.isNotEmpty() && desc.isNotEmpty()){
            viewModel.insertNote(EntityClass(title,desc))
            Toast.makeText(this, "$title is INSERTED", Toast.LENGTH_SHORT).show()
            finish()
        }

    }
}