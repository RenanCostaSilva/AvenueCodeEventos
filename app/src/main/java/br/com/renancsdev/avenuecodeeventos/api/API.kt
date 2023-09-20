package br.com.renancsdev.avenuecodeeventos.api

import br.com.renancsdev.avenuecodeeventos.model.Evento
import br.com.renancsdev.avenuecodeeventos.model.Resultados
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface API {
    
    @GET("events/{id}")
    fun busarEventoPorID(@Path("id") id: Int): Call<Evento>

    @GET("events")
    fun busarEventos(): Call<ArrayList<Evento>>

    @FormUrlEncoded
    @POST("checkin")
    fun fazerCheckIn(@Field("name") nome: String , @Field("email") email: String ,  @Field("eventId") id: Int): Call<Evento>
}