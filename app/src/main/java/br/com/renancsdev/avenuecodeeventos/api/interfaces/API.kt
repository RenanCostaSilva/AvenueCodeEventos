package br.com.renancsdev.avenuecodeeventos.api.interfaces

import br.com.renancsdev.avenuecodeeventos.model.Evento
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface API {

    @GET("events/{id}")
    suspend fun buscarEventoPorIDLiveData(@Path("id") id: Int): Response<Evento>


    @GET("events")
    suspend fun buscarEventosLiveData(): Response<ArrayList<Evento>>

    @FormUrlEncoded
    @POST("checkin")
    suspend fun fazerCheckInLiveData(@Field("name") nome: String , @Field("email") email: String ,  @Field("eventId") id: Int): Response<Evento>

}