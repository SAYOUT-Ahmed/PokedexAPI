package com.example.pokdexahmed.pokeapi;

import com.example.pokdexahmed.models.Pokemon;
import com.example.pokdexahmed.models.PokemonRequest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeApiServ {
    @GET("pokemon")
    Call<PokemonRequest> jibPokimounat(@Query("limit") int limit, @Query("offset") int offset);

    @GET("{dexNumOrName}/")
    Call<Pokemon> getPokemon(@Path("dexNumOrName") String dexNumOrName);
}
