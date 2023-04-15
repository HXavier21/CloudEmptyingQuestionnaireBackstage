package com.example.questionnairebackstage.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ResultDao{
    @Insert
    fun insertAll(vararg results:Result)

    @Query("select * from result")
    fun getAll():List<Result>
}