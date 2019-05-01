package com.up.fotodasortedanasa

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query


interface NasaApodService {

    // recebe os dados direto
    @GET("apod?api_key=DEMO_KEY")
    //fun listPictutres(): Observable<List<NasaApod>>
    fun showPicture():Observable<NasaApod>

    // recebe os dados passando duas query
    @GET("apod?")
    fun showPictureByDate(@Query("api_key") api_key: String,
                          @Query("date") dataPicture: String)
            :Observable<NasaApod>
}