package com.example.mydemo

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "demoTable")
class EntityClass(
    val title: String,
    val description: String,
    @PrimaryKey(autoGenerate = true) var id: Int = 0): Serializable{
}