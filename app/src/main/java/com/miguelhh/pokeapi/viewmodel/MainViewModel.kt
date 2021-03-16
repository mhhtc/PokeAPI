package com.miguelhh.pokeapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miguelhh.pokeapi.domain.data.network.PokeRepo
import com.miguelhh.pokeapi.model.PokeItem
import com.miguelhh.pokeapi.model.PokeDetail

class MainViewModel:ViewModel() {

    private val repo = PokeRepo()

    fun fetchPokeData(): LiveData<MutableList<PokeItem>>{
        val mutableData = MutableLiveData<MutableList<PokeItem>>()
        repo.getPokeData().observeForever{
            mutableData.value =  it
        }

        return mutableData
    }


    fun fetchPokeDetailData(pokeId:Int): LiveData<PokeDetail>{
        val mutableData = MutableLiveData<PokeDetail>()
        repo.getPokeDetailData(pokeId).observeForever{
            mutableData.value =  it
        }

        return mutableData
    }

}