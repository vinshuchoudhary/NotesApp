package com.example.mydemo

import android.content.Context
import android.content.Intent
import android.media.Image
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Update
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.RandomAccess

class RVAdapter(private val context: Context , private val listener: INotesRVAdapter) : RecyclerView.Adapter<RVAdapter.RVViewHolder>() {

    private val allNotes = ArrayList<EntityClass>()


    inner class RVViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val title = itemView.findViewById<TextView>(R.id.title)
        val desc = itemView.findViewById<TextView>(R.id.description)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
        val borderColor = itemView.findViewById<View>(R.id.border)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val viewHolder = RVViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))

        viewHolder.deleteButton.setOnClickListener {
            listener.onItemClicked(allNotes[viewHolder.adapterPosition])
        }

        viewHolder.title.setOnClickListener {
            val intent = Intent(context,UpdateActivity::class.java)
            intent.putExtra("code",allNotes[viewHolder.adapterPosition])
            context.startActivity(intent)
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        val currentNote = allNotes[position]
        holder.title.text = currentNote.title
        holder.desc.text = currentNote.description
        val colorList = holder.itemView.resources.getIntArray(R.array.random_color)

        val chosenColor = colorList[Random().nextInt(colorList.size)]
        holder.borderColor.setBackgroundColor(chosenColor)

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList : List<EntityClass>){
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

}

interface INotesRVAdapter{

    fun onItemClicked(note: EntityClass)

}