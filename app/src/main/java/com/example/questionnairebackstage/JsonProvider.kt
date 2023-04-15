package com.example.questionnairebackstage

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import android.util.Log
import com.example.questionnairebackstage.data.database.Result
import com.tencent.mmkv.MMKV

private const val TAG = "JsonProvider"

val kv = MMKV.defaultMMKV()


class JsonProvider : ContentProvider() {
    private val jsonIndex = 0
    private val nameIndex = 1
    private val authority = "com.example.questionnairebackstage.provider"

    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority, "json", jsonIndex)
        matcher.addURI(authority, "name", nameIndex)
        matcher
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun getType(uri: Uri): String? = when (uriMatcher.match(uri)) {
        jsonIndex -> "vnd.android.cursor.dir/vnd.com.example.questionnairebackstage.provider.json"
        nameIndex -> "vnd.android.cursor.dir/vnd.com.example.questionnairebackstage.provider.name"
        else -> null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        when (uriMatcher.match(uri)) {
            jsonIndex->{
                kv.encode("json",values?.getAsString("json")?:"")
            }
            nameIndex -> {
                val result = Result(
                    name = values?.getAsString("name")?:"",
                    json = kv.decodeString("json")?:""
                )
                Log.d(TAG, result.json)
                Log.d(TAG, result.name)
                App.db.resultDao().insertAll(result)
            }
        }
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        TODO("Not yet implemented")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        TODO("Not yet implemented")
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        TODO("Not yet implemented")
    }

}