package com.example.questionnairebackstage.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Result(
    @PrimaryKey (autoGenerate = true) val id:Int = 0,
    val name:String = "",
    val json:String = ""
)