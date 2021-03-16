package com.miguelhh.pokeapi.domain.api

import com.miguelhh.pokeapi.model.PokeList
import com.miguelhh.pokeapi.model.PokeDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClientServices {

    @GET("pokemon")
    fun getPokeList(@Query("limit") limit: Int): Call<PokeList>

    @GET("pokemon/{id}")
    fun getPokeDetail(@Path("id") pokeId: Int): Call<PokeDetail>

}