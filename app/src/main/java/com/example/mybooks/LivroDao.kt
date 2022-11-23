package com.example.mybooks

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log

class LivroDao(context: Context) {
    var banco = DbHelper(context)

    fun insert(livro: Livro): String {
        val db = banco.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(LIVRO_TITULO, livro.titulo)
        contentValues.put(LIVRO_AUTOR, livro.autor)
        contentValues.put(LIVRO_TIPO, livro.tipo)
        contentValues.put(LIVRO_PAGINAS, livro.paginas)
        contentValues.put(LIVRO_LIDAS, livro.lidas)

        var resp_id = db.insert(TABLE_LIVROS, null, contentValues)
        val msg = if (resp_id != -1L) {
            "Inserido com sucesso"
        } else {
            "Erro ao inserir"
        }
        db.close()
        return msg
    }

    fun update(livro: Livro): Boolean {
        val db = banco.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(LIVRO_ID, livro.id)
        contentValues.put(LIVRO_TITULO, livro.titulo)
        contentValues.put(LIVRO_AUTOR, livro.autor)
        contentValues.put(LIVRO_TIPO, livro.tipo)
        contentValues.put(LIVRO_PAGINAS, livro.paginas)
        contentValues.put(LIVRO_LIDAS, livro.lidas)

        db.insertWithOnConflict(TABLE_LIVROS, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()

        return true
    }

    fun remove(livro: Livro): Int {
        val db = banco.writableDatabase
        return db.delete(TABLE_LIVROS, "ID =?", arrayOf(livro.id.toString()))
    }

    fun getAll(): ArrayList<Livro>{
        val db = banco.readableDatabase
        val sql = "SELECT * FROM "+TABLE_LIVROS
        val cursor = db.rawQuery(sql, null)
        val livros = ArrayList<Livro>()
        while(cursor.moveToNext()){
            val livro = livroFromCursor(cursor)
            livros.add(livro)
        }
        cursor.close()
        db.close()
        return livros
    }

    fun getById(id: Int): Livro? {
        val db = banco.readableDatabase
        val sql = "SELECT * FROM "+TABLE_LIVROS+" WHERE ID = '$id'"
        val cursor = db.rawQuery(sql, arrayOf(id.toString()))
        val livro = if(cursor.moveToNext()){
            livroFromCursor(cursor)
        } else {
            null
        }
        cursor.close()
        db.close()
        return livro
    }



    @SuppressLint("Range")
    private fun livroFromCursor(cursor: Cursor): Livro {
        val id = cursor.getInt(cursor.getColumnIndex(LIVRO_ID))
        val titulo = cursor.getString(cursor.getColumnIndex(LIVRO_TITULO))
        val autor = cursor.getString(cursor.getColumnIndex(LIVRO_AUTOR))
        val tipo = cursor.getString(cursor.getColumnIndex(LIVRO_TIPO))
        val paginas = cursor.getInt(cursor.getColumnIndex(LIVRO_PAGINAS))
        val lidas = cursor.getInt(cursor.getColumnIndex(LIVRO_LIDAS))
        return Livro(id, titulo, autor, tipo, paginas, lidas)
    }

    fun getLidos(): MutableList<Livro> {
        val db = banco.readableDatabase
        val sql = "SELECT * FROM "+TABLE_LIVROS+" WHERE LIDAS = PAGINAS"
        val cursor = db.rawQuery(sql, null)
        val livros = ArrayList<Livro>()
        while(cursor.moveToNext()){
            val livro = livroFromCursor(cursor)
            livros.add(livro)
        }
        cursor.close()
        db.close()
        return livros
    }

    fun getNaoLidos(): MutableList<Livro> {
        val db = banco.readableDatabase
        val sql = "SELECT * FROM "+TABLE_LIVROS+" WHERE LIDAS < PAGINAS"
        val cursor = db.rawQuery(sql, null)
        val livros = ArrayList<Livro>()
        while(cursor.moveToNext()){
            val livro = livroFromCursor(cursor)
            livros.add(livro)
        }
        cursor.close()
        db.close()
        return livros
    }
}