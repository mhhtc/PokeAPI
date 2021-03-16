package com.miguelhh.pokeapi.domain.api

import com.miguelhh.pokeapi.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ClientAPI {

    companion object {
        fun getBaseUrl(): String {
            return BuildConfig.API_URL
        }

        private var retrofit: Retrofit? = null
        val client: Retrofit
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(getBaseUrl())
                        .build()
                }
                return retrofit!!
            }
    }

}