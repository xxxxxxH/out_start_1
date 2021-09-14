package net.http

import net.entity.DetailsEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RequestService {
    @GET("{date}/{key}")
    fun getInfo(@Path("date") date: String, @Path("key") key: String): Call<DetailsEntity>
}