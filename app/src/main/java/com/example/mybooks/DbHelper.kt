package com.example.mybooks

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DbHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE $TABLE_LIVROS ($LIVRO_ID INTEGER PRIMARY KEY " +
                "AUTOINCREMENT, $LIVRO_TITULO TEXT,$LIVRO_AUTOR TEXT,$LIVRO_TIPO TEXT,$LIVRO_PAGINAS INTEGER,$LIVRO_LIDAS INTEGER)"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME)
        onCreate(db)
    }
}