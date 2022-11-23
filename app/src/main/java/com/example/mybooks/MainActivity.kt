package com.example.mybooks

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private var livrosList = mutableListOf<Livro>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        updateAdapter()
        initRecyclerView()
        var cadastrar = findViewById<FloatingActionButton>(R.id.cadastrar)
        cadastrar.setOnClickListener {
            val intent = Intent(this, AdicionarLivroActivity::class.java)
            startActivity(intent)
        }
        var lidosNaoLidos = findViewById<TextView>(R.id.lidosNaoLidos)
        lidosNaoLidos.setOnClickListener {
            val intent = Intent(this, LidosNaoLidosActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        updateAdapter()
        initRecyclerView()
    }

    private fun updateAdapter() {
        var rvDados = findViewById<RecyclerView>(R.id.rvDados)
        var livroDao = LivroDao(this)
        livrosList.clear()
        livrosList = livroDao.getAll()
        rvDados.adapter?.notifyDataSetChanged()
    }

    private fun initRecyclerView() {
        var rvDados = findViewById<RecyclerView>(R.id.rvDados)
        val adapter2 = LivroAdapter(livrosList)
        rvDados.adapter = adapter2
        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvDados.layoutManager = layout
    }

}

