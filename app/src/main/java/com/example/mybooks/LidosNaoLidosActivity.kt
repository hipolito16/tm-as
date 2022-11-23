package com.example.mybooks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LidosNaoLidosActivity : AppCompatActivity() {

    private var livroLidos = mutableListOf<Livro>()
    private var livroNaoLidos = mutableListOf<Livro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lidos_nao_lidos)
        updateLidosNaoLidos()
        initRecyclerView()
    }

    private fun updateLidosNaoLidos() {
        var rvLidos = findViewById<RecyclerView>(R.id.rvLidos)
        var rvNaoLidos = findViewById<RecyclerView>(R.id.rvNaoLidos)
        val livroDao = LivroDao(this)
        livroLidos = livroDao.getLidos()
        livroNaoLidos = livroDao.getNaoLidos()
        rvLidos.adapter?.notifyDataSetChanged()
        rvNaoLidos.adapter?.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        var rvLidos = findViewById<RecyclerView>(R.id.rvLidos)
        var rvNaoLidos = findViewById<RecyclerView>(R.id.rvNaoLidos)
        val adapterLidos = LivroAdapterLidosNaoLidos(livroLidos)
        val adapterNaoLidos = LivroAdapterLidosNaoLidos(livroNaoLidos)
        rvLidos.adapter = adapterLidos
        rvNaoLidos.adapter = adapterNaoLidos
        val layoutLidos = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val layoutNaoLidos = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvLidos.layoutManager = layoutLidos
        rvNaoLidos.layoutManager = layoutNaoLidos
    }
}