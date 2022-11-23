package com.example.mybooks

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mybooks.databinding.ActivityLidasBinding

class LidasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLidasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLidasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val livro = intent.getParcelableExtra<Livro>("livro")

        binding.textView2.setText("Total de Páginas : " + livro?.paginas.toString())

        binding.lidas.setOnClickListener {
            if (binding.lidas.text.toString().toInt() > livro?.paginas.toString().toInt()) {
                Toast.makeText(this,
                    "Páginas lidas não pode ser maior que o total de páginas",
                    Toast.LENGTH_SHORT).show()
            } else {
                var livroUP = Livro(livro?.id,
                    livro?.titulo,
                    livro?.autor,
                    livro?.tipo,
                    livro?.paginas,
                    binding.lidas.text.toString().toInt())
                var livroDao = LivroDao(this)
                livroDao.update(livroUP)
                onBackPressed()
            }
        }
    }
}