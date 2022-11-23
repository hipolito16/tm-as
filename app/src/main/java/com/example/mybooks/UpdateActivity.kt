package com.example.mybooks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mybooks.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val livro = intent.getParcelableExtra<Livro>("livro")

        binding.titulo.setText(livro?.titulo.toString())
        binding.autor.setText(livro?.autor.toString())
        binding.tipo.setText(livro?.tipo.toString())
        binding.paginas.setText(livro?.paginas.toString())

        binding.update.setOnClickListener {
            if (binding.titulo.text.toString().isEmpty() || binding.autor.text.toString()
                    .isEmpty() || binding.tipo.text.toString()
                    .isEmpty() || binding.paginas.text.toString().isEmpty()
            ) {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else if (binding.paginas.text.toString().toInt() < 1) {
                Toast.makeText(this, "Páginas não pode ser menor que 1", Toast.LENGTH_SHORT).show()
            } else if (livro?.lidas!! > binding.paginas.text.toString().toInt()) {
                Toast.makeText(this,
                    "Páginas não pode ser menor que o total de páginas lidas",
                    Toast.LENGTH_SHORT).show()
            } else {
                var livroUP = Livro(livro?.id,
                    binding.titulo.text.toString(),
                    binding.autor.text.toString(),
                    binding.tipo.text.toString(),
                    binding.paginas.text.toString().toInt(),
                    livro?.lidas)
                var livroDao = LivroDao(this)
                livroDao.update(livroUP)
                onBackPressed()
            }
        }
    }
}