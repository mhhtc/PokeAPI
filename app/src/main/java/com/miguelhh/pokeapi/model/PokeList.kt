package com.miguelhh.pokeapi.model

data class PokeList(
    val count: Int,
    val results: List<PokeItem>
)