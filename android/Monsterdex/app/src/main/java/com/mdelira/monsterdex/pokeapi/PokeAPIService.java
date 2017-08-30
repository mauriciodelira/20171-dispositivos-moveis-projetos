package com.mdelira.monsterdex.pokeapi;


import com.mdelira.monsterdex.models.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeAPIService {

    // Todos os retornos do Retrofit é uma Call, que é de uma classe definida
    @GET("pokemon") // vai lançar uma requisição GET pra url base + o que tá entre parênteses
    Call<PokemonResponse> obterListaPokemon();
}
