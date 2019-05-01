package com.up.fotodasortedanasa

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var recyclerAdapter: NasaApodRecyclerAdapter? = null
    private var nasaList: ArrayList<NasaApod>? = null

    private lateinit var myCompositeDisposable: CompositeDisposable
    private lateinit var recyclerView: RecyclerView

    private val BASE_URL = "https://api.nasa.gov/planetary/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myCompositeDisposable = CompositeDisposable()
        recyclerView = findViewById(R.id.recyclerNasa)

        initRecyclerView()
        loadData()
    }

    private fun loadData() {
        //Define uma requisicao do tipo Retrofit
        val requestInterface = Retrofit.Builder()
            .baseUrl(BASE_URL) // Seta a url da api
            .addConverterFactory(GsonConverterFactory.create()) // especifica qual conversor de serialização e desserialização será utilizado, no caso o gon
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // acidiona o adaptado de chamada (antigo call) para retornar o tipo rxjava
            .build().create(NasaApodService::class.java) // cria a instancia do retrofit


       /* myCompositeDisposable.add(requestInterface.showPicture()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(this::handleResponse, this::handleError))
       */
        // adicionar todos os rxjava disponivieis ap composite disposable
        myCompositeDisposable.add(requestInterface
            .showPictureByDate("DEMO_KEY", "2019-04-21")
            .observeOn(AndroidSchedulers.mainThread()) // envia a notificação observada para a thread da interface grágica
            .subscribeOn(Schedulers.io()) // sobrescreve para a via observable do main da thread da ui
            .subscribe(this::handleResponse, this::handleError))
    }

    // adiciona os itens no recycler view se der tudo certo
    private fun handleResponse(nasaApod: NasaApod){
        nasaList = ArrayList()
        nasaList?.add(nasaApod)

        recyclerAdapter = NasaApodRecyclerAdapter(nasaList!!)
        recyclerView.adapter = recyclerAdapter

    }

    // apresena o erro caso ocorra
    private fun handleError(error: Throwable){
        Log.d("TAG error: ", error.localizedMessage)
        Toast.makeText(this, "ERRO: ${error.localizedMessage}",
            Toast.LENGTH_LONG).show()
    }

    // configuração da criação do recycler view
    private fun initRecyclerView() {
        recyclerView.setHasFixedSize(true)

        val layoutManager : RecyclerView.LayoutManager =
            LinearLayoutManager(this)

        recyclerView.layoutManager = layoutManager
    }

    private fun pegaData(dia: Int): String{
        val df = SimpleDateFormat("YYYY-MM-dd")

        var dataFormatada = ""

        val cal  = Calendar.getInstance()
        cal.add(Calendar.DATE, -dia)
        dataFormatada = df.format(cal.time)

        return dataFormatada
    }
}
