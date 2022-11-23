package com.example.mybooks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mybooks.databinding.ActivityAdicionarLivroBinding

class AdicionarLivroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdicionarLivroBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAdicionarLivroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.insert.setOnClickListener {
            if (binding.titulo.text.toString().isEmpty() || binding.autor.text.toString()
                    .isEmpty() || binding.tipo.text.toString()
                    .isEmpty() || binding.paginas.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else if (binding.paginas.text.toString().toInt() < 1) {
                Toast.makeText(this, "Páginas não pode ser menor que 1", Toast.LENGTH_SHORT).show()
            } else {
                var livro = Livro(null,
                    binding.titulo.text.toString(),
                    binding.autor.text.toString(),
                    binding.tipo.text.toString(),
                    binding.paginas.text.toString().toInt(),
                    0)
                var livroDao = LivroDao(this)
                livroDao.insert(livro)
                onBackPressed()
            }
        }
    }
}
