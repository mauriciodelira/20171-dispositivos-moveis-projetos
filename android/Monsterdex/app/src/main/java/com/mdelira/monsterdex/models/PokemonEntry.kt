package com.mdelira.monsterdex.models

/* Classe em Kotlin
modelo:
class [nomeClasse] [optional: (construtor com variável : tipo)

val: imutáveis
var: mutáveis

https://medium.com/@dbottillo/kotlin-by-examples-class-and-properties-d57bf1d8dfa0
 */
class PokemonEntry(url: String, name: String) {
    val name = name
    val url = url

    override fun toString(): String {
        return "PokemonEntry(name='$name', url='$url')"
    }
}