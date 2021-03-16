package com.miguelhh.pokeapi.model

data class PokeItem(
    val name: String,
    val url: String
){

    fun getId():Int{
        val arrUrl:List<String> = url.split("/").map { it -> it.trim() }
        val dato =  arrUrl[arrUrl.size - 2]
        return dato.toInt()
    }
}