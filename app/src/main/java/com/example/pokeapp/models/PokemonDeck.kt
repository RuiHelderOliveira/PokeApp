package com.example.pokeapp.models

data class PokemonDeck(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: Array<PokemonNames>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PokemonDeck

        if (count != other.count) return false
        if (next != other.next) return false
        if (previous != other.previous) return false
        if (!results.contentEquals(other.results)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = count
        result = 31 * result + next.hashCode()
        result = 31 * result + previous.hashCode()
        result = 31 * result + results.contentHashCode()
        return result
    }
}

data class PokemonNames(
    val name: String,
    val url: String
)

