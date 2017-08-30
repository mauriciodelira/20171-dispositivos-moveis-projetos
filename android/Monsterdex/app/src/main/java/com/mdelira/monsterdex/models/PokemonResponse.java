package com.mdelira.monsterdex.models;


import java.util.ArrayList;

// classe que receberá o JSON parseado da URL pokeapi.co/api/v2/pokemon
public class PokemonResponse {
    // o ideal pelo retrofit é que o nome da variável aqui seja o mesmo que o do JSON
    private ArrayList<PokemonEntry> results;
    private String previous = null;
    private String next = null;

    public ArrayList<PokemonEntry> getResults() {
        return results;
    }

    public void setResults(ArrayList<PokemonEntry> results) {
        this.results = results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }
}
