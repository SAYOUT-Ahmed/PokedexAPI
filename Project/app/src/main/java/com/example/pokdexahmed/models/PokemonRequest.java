package com.example.pokdexahmed.models;

import java.util.ArrayList;

public class PokemonRequest {
    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }

    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
