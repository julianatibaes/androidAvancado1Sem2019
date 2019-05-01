package com.up.fotodasortedanasa

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_list_nasa.view.*

class NasaApodRecyclerAdapter (private val nasaList: ArrayList<NasaApod>):
    RecyclerView.Adapter<NasaApodRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
         val view = LayoutInflater.from(parent.context).
                 inflate(R.layout.row_list_nasa, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = nasaList.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Passar para a posicao onde casa item será exibido
        holder.bind(nasaList[position])
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(nasaApod: NasaApod){
            itemView.txtTitulo.text  = nasaApod.titulo
            itemView.txtDescricao.text = nasaApod.explicacao

            if (!nasaApod.url.equals("") &&
                nasaApod.tipo_midia == "image"){
                Picasso.get().load(nasaApod.url).
                    into(itemView.imageView)
                itemView.imageView.visibility = View.VISIBLE
            } else itemView.imageView.visibility = View.GONE
        }

    }
}