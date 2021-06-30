package com.example.mydemo

import android.os.Bundle
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mydemo.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var viewModel: ViewModelClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ViewModelClass::class.java)

//        val list = viewModel.allNotes.value!!
        val intent = intent
        val code = intent.getSerializableExtra("code") as EntityClass
        binding.title.setText(code.title)
        binding.description.setText(code.description)

        binding.updateButton.setOnClickListener {
            val title = binding.title.text.toString()
            val desc = binding.description.text.toString()
            viewModel.updateNote(title,desc,code.id)
            finish()
        }

    }
}