package com.up.conectafirebase.ui.activity.fragment


import android.os.Bundle
import android.renderscript.Sampler
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.*

import com.up.conectafirebase.R
import com.up.conectafirebase.adapter.ListaFilmeAdapter
import com.up.conectafirebase.model.Filme


class ListaFilmeFragment : Fragment() {

    lateinit var myRef: DatabaseReference
    private val listaFilmes: MutableList<Filme> = mutableListOf()

    lateinit var listaFilmesRecycler: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        myRef = FirebaseDatabase.getInstance().getReference()

        populaFilmes(myRef)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val inf = inflater.inflate(R.layout.fragment_lista_filme, container, false)

        listaFilmesRecycler = inf.findViewById<RecyclerView>(R.id.lista_recycler)

        return inf
    }

    override fun onResume() {
        preparaListaFilmes()
        montaListaRecycler(listaFilmesRecycler)
        super.onResume()

    }

    private fun preparaListaFilmes(){
        val menuListener = object : ValueEventListener{
            // mostra mensagem de erro caso algo ocorra algum problema na conexao ou para obter os dados
            override fun onCancelled(p0: DatabaseError) {
                Log.d("TAG: ", "loadPost: onCancelled ${p0.toException()}")
            }

            // para cada valor atualizado no banco, vou repopular a lista
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                listaFilmes.clear()
                dataSnapshot.children.mapNotNullTo(listaFilmes) {
                    it.getValue<Filme>(Filme::class.java)
                }
            }
        }
        myRef.child("filmes").addListenerForSingleValueEvent(menuListener)
    }

    private fun populaFilmes(firebaseData: DatabaseReference){
        val popFilmes: List<Filme> = mutableListOf(
            Filme(nome = "Harry Filme", lancamento = "2010"),
            Filme(nome = "Vindores: Ultimato", lancamento = "2019"),
            Filme(nome = "Dumbo", lancamento = "2019")
        )
        popFilmes.forEach {
            val key = firebaseData.child("filmes").push().key
            it.id = key!!
            firebaseData.child("filmes").child(key).setValue(it)
        }
    }

    private fun montaListaRecycler(listaFilmesRecycler: RecyclerView){
        listaFilmesRecycler.let {
            it.adapter = ListaFilmeAdapter(context, listaFilmes)
            val layoutManager = StaggeredGridLayoutManager(1,
                StaggeredGridLayoutManager.VERTICAL)
            it.layoutManager = layoutManager
        }
        setRecyclerViewItemTouchListener()
    }

    private fun setRecyclerViewItemTouchListener(){
        val itemTouchCallBack = object : ItemTouchHelper.SimpleCallback(
            0, ItemTouchHelper.LEFT or
                    ItemTouchHelper.RIGHT){
            override fun onMove(p0: RecyclerView, p1: RecyclerView.ViewHolder, p2: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(p0: RecyclerView.ViewHolder, p1: Int) {
                val position = p0.adapterPosition
                removeFilme(position)
                listaFilmes.removeAt(position)
                listaFilmesRecycler.adapter!!.notifyItemRemoved(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallBack)
        itemTouchHelper.attachToRecyclerView(listaFilmesRecycler)

    }


    private fun removeFilme(position: Int){
        myRef.child("filmes")
            .child(listaFilmes.get(position).id)
            .setValue(null)
    }
}
