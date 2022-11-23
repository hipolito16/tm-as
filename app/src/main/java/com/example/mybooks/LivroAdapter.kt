package com.example.mybooks

import android.app.AlertDialog
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LivroAdapter(private val livros: List<Livro>) : RecyclerView.Adapter<LivroAdapter.VH>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.livro_item, parent, false)
        val vh = VH(v)

        vh.imgLidasLivro.setOnClickListener {
            val livro = livros[vh.adapterPosition]
            val intent = Intent(parent.context, LidasActivity::class.java)
            intent.putExtra("livro", livro)
            parent.context.startActivity(intent)
        }
        vh.imgUpdateLivro.setOnClickListener {
            val livro = livros[vh.adapterPosition]
            val intent = Intent(parent.context, UpdateActivity::class.java)
            intent.putExtra("livro", livro)
            parent.context.startActivity(intent)
        }
        vh.imgDeleteLivro.setOnClickListener {
            var builder = AlertDialog.Builder(parent.context)
            builder.setTitle("Excluir")
            builder.setMessage("Deseja excluir o livro?")
            builder.setCancelable(true)
            var icon = parent.context.resources.getDrawable(android.R.drawable.ic_dialog_alert)
            icon.setTint(parent.context.resources.getColor(R.color.black))
            builder.setIcon(icon)
            builder.setPositiveButton("Sim") { dialog, which ->
                val livro = livros[vh.adapterPosition]
                val livroDao = LivroDao(parent.context)
                livroDao.remove(livro)
                val intent = Intent(parent.context, MainActivity::class.java)
                parent.context.startActivity(intent)
            }
            builder.setNegativeButton("Não") { dialog, which ->
                dialog.dismiss()
            }
            builder.show()
        }
        return vh
    }

    override fun getItemCount(): Int {
        return livros.size
    }


    override fun onBindViewHolder(holder: VH, position: Int) {
        var livro = livros[position]
        holder.txtTitulo.text = livro.titulo
        holder.txtAutor.text = livro.autor
        holder.txtTipo.text = livro.tipo
        holder.txtPaginas.text = livro.lidas.toString() + " / " + livro.paginas.toString()
        holder.imgUpdateLivro.setImageResource(R.drawable.ic_edit)
        holder.imgDeleteLivro.setImageResource(R.drawable.ic_delete)
        holder.imgLidasLivro.setImageResource(R.drawable.ic_book)
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {

        var txtTitulo = view.findViewById<TextView>(R.id.txtTitulo)
        var txtAutor = view.findViewById<TextView>(R.id.txtAutor)
        var txtTipo = view.findViewById<TextView>(R.id.txtTipo)
        var txtPaginas = view.findViewById<TextView>(R.id.txtPaginas)
        var imgUpdateLivro = view.findViewById<ImageButton>(R.id.imgUpdateLivro)
        var imgDeleteLivro = view.findViewById<ImageButton>(R.id.imgDeleteLivro)
        var imgLidasLivro = view.findViewById<ImageButton>(R.id.imgLidasLivro)

        init {
            txtTitulo = view.findViewById(R.id.txtTitulo)
            txtAutor = view.findViewById(R.id.txtAutor)
            txtTipo = view.findViewById(R.id.txtTipo)
            txtPaginas = view.findViewById(R.id.txtPaginas)
            imgUpdateLivro = view.findViewById(R.id.imgUpdateLivro)
            imgDeleteLivro = view.findViewById(R.id.imgDeleteLivro)
            imgLidasLivro = view.findViewById(R.id.imgLidasLivro)
        }

    }
}

class LivroAdapterLidosNaoLidos(private val livros: List<Livro>) :
    RecyclerView.Adapter<LivroAdapterLidosNaoLidos.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v =
            LayoutInflater.from(parent.context).inflate(R.layout.lido_nao_lido_item, parent, false)
        val vh = VH(v)
        return vh
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        var livro = livros[position]
        holder.txtTitulo.text = livro.titulo
        holder.txtAutor.text = livro.autor
        holder.txtTipo.text = livro.tipo
        holder.txtPaginas.text = livro.lidas.toString() + " / " + livro.paginas.toString()
    }

    override fun getItemCount(): Int {
        return livros.size
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {

        var txtTitulo = view.findViewById<TextView>(R.id.txtTitulo)
        var txtAutor = view.findViewById<TextView>(R.id.txtAutor)
        var txtTipo = view.findViewById<TextView>(R.id.txtTipo)
        var txtPaginas = view.findViewById<TextView>(R.id.txtPaginas)

        init {
            txtTitulo = view.findViewById(R.id.txtTitulo)
            txtAutor = view.findViewById(R.id.txtAutor)
            txtTipo = view.findViewById(R.id.txtTipo)
            txtPaginas = view.findViewById(R.id.txtPaginas)
        }
    }
}
