package com.example.questionnairebackstage.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class ResultDatabase:RoomDatabase(){
    abstract fun resultDao():ResultDao
}