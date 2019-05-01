package com.up.conectafirebase.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.up.conectafirebase.R
import com.up.conectafirebase.model.Filme
import kotlinx.android.synthetic.main.item_view_filme.view.*


class ListaFilmeAdapter (
    private val context: Context?,
    private val filmes: List<Filme>):
    RecyclerView.Adapter<ListaFilmeAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        // inflar o item de view na activity através do fragment
        val view = LayoutInflater.from(context).
            inflate(R.layout.item_view_filme,p0,false)

        return ViewHolder(view)
    }


    override fun getItemCount(): Int {
        // retorna o tamanho da lista
        return filmes.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val filme = filmes[position]

        // atribuindo uma ação ao clicar no item
        holder.let {
            // atribindo no meu item de view o valor do item da lista
            // naquela posição
            it.nome.text = filme.nome

            it.itemView.setOnClickListener({
                Toast.makeText(context, "Olha só",
                    Toast.LENGTH_SHORT).show()
            })
        }
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nome = itemView.txt_item_nome
    }
}