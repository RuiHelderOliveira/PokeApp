package com.example.pokeapp.models

data class Pokemon(
    var id: Int?,
    var name: String?,
    var base_experience: Int?,
    var height: Int?,
    var is_default: Boolean?,
    var order: Int?,
    var weight: Int?,
    var sprites: PokemonSprites?
)



