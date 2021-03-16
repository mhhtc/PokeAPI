package com.miguelhh.pokeapi.domain.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.miguelhh.pokeapi.domain.api.ClientAPI
import com.miguelhh.pokeapi.domain.api.ClientServices
import com.miguelhh.pokeapi.model.PokeItem
import com.miguelhh.pokeapi.model.PokeList
import com.miguelhh.pokeapi.model.PokeDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PokeRepo {

    private var apiclient: ClientServices? = null

    init {
        apiclient = ClientAPI.client.create(ClientServices::class.java)
    }

    fun getPokeData(): LiveData<MutableList<PokeItem>> {
        val mutableData = MutableLiveData<MutableList<PokeItem>>()
        val call = apiclient?.getPokeList(1000)
        call?.enqueue(object : Callback<PokeList> {
            override fun onFailure(call: Call<PokeList>, t: Throwable?) {
                Log.d("failure", t.toString())
            }
            override fun onResponse(call: Call<PokeList>, response: Response<PokeList>?) {
                if (response?.isSuccessful!!) {
                    val resultsList: PokeList = response.body() as PokeList
                    mutableData.value = resultsList.results as MutableList<PokeItem>
                }
            }
        })
        return mutableData
    }


    fun getPokeDetailData(pokeId:Int): LiveData<PokeDetail> {
        val mutableData = MutableLiveData<PokeDetail>()
        val call = apiclient?.getPokeDetail(pokeId)
        call?.enqueue(object : Callback<PokeDetail> {
            override fun onFailure(call: Call<PokeDetail>, t: Throwable?) {
                Log.d("failure", t.toString())
            }
            override fun onResponse(call: Call<PokeDetail>, response: Response<PokeDetail>?) {
                if (response?.isSuccessful!!) {
                    val result: PokeDetail = response.body() as PokeDetail
                    mutableData.value = result
                }
            }
        })
        return mutableData
    }

}