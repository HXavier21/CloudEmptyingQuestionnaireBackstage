package com.example.questionnairebackstage

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.questionnairebackstage.data.database.ResultDatabase
import com.tencent.mmkv.MMKV

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        val rootDir = MMKV.initialize(this)
        context = this.applicationContext
        db = Room.databaseBuilder(
            applicationContext,
            ResultDatabase::class.java, "db"
        ).build()
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        lateinit var db:ResultDatabase
    }
}